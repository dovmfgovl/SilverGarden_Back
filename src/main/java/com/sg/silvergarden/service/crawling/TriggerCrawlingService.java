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

    // 매일 밤 12시에 크롤링을 실행예정(우선 월요일)
//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "00 00 00 ? * WED")
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
