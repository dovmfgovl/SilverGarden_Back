package com.sg.silvergarden.service.crawling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sg.silvergarden.controller.crawling.CrawlingController;
import com.sg.silvergarden.dao.crawling.CrawlingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {
    Logger logger = LoggerFactory.getLogger(CrawlingController.class);

    @Autowired
    private CrawlingDao crawlingDao;

    // 매주 금요일 12시에 크롤링을 실행
    @Scheduled(cron = "0 20 14 ? * SUN")
    public void triggerCrawling() {
        logger.info("///////////CrawlingService-triggerCrawling - " + java.time.LocalDateTime.now());
        try {
            // Node.js 프로세스 실행
            Process process = Runtime.getRuntime().exec("node /Users/jyahn/dev/SilverGarden/SilverGarden_Back/src/main/java/com/sg/silvergarden/service/crawling/puppeteer-script.js");
            // 프로세스 실행 결과 수집
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            process.waitFor();

            if (result.length() > 0) {
                List<Map<String, Object>> pList = parseCrawledData(result.toString());
                if (!pList.isEmpty()) {
                    logger.info("Crawled Data: " + pList);
                    crawlingDao.dataInsert(pList); // 필요타입 : string
                } else {
                    logger.warn("Crawled data is empty.");
                }
            } else {
                logger.warn("Crawling result is empty.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Map<String, Object>> parseCrawledData(String crawledData) {
//        logger.info("Crawled data received: " + crawledData);

        List<Map<String, Object>> convertedData = new ArrayList<>();

        try {
            Gson gson = new Gson();
            // JSON 파싱 시도
            convertedData = gson.fromJson(crawledData, List.class);
            logger.info(convertedData.toString());
            // 파싱이 성공하면 로그에 성공 메시지 출력
            logger.info("JSON parsing successful");
        } catch (JsonIOException | JsonSyntaxException e) {
            // 예외 발생 시 로그에 에러 메시지 및 StackTrace 출력
            logger.error("Error parsing JSON", e);
        }

        return convertedData;
    }

    public void dataInsert(String data) {
        try {
            logger.info(data);
//            crawlingDao.dataInsert(data);

        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 로그 출력 및 필요한 예외 처리 추가
        }
    }
}
