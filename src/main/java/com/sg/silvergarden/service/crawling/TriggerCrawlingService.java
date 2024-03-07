package com.sg.silvergarden.service.crawling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class TriggerCrawlingService {
    Logger logger = LoggerFactory.getLogger(TriggerCrawlingService.class);

//    @Scheduled(cron = "00 19 04 ? * THU")
    // 매일 밤 12시에 크롤링을 실행예정
    @Scheduled(cron = "00 32 08 * * ?")
    public void triggerCrawling() {
        logger.info("///////////triggerCrawling-triggerCrawling - " + java.time.LocalDateTime.now());
        try {
            Process process = Runtime.getRuntime().exec("node /Users/jyahn/dev/SilverGarden/SilverGarden_Back/src/main/java/com/sg/silvergarden/service/crawling/puppeteer-script.js");
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
