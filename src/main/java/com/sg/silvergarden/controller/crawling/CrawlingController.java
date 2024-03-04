package com.sg.silvergarden.controller.crawling;
import com.sg.silvergarden.service.crawling.CrawlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crawling/*")
public class CrawlingController {
    Logger logger = LoggerFactory.getLogger(CrawlingController.class);
    @Autowired
    private CrawlingService crawlingService;


    @PostMapping("dataInsert")
    public String dataInsert(@RequestBody String data) {
        logger.info("CrawlingController-dataInsert");
        logger.info(data.toString());
        try {
            crawlingService.dataInsert(data);
            return "success"; // 성공적으로 데이터베이스에 저장되었을 때 메시지를 반환
        } catch (Exception e) {
            logger.error("Error inserting data:", e);
            return "error"; // 데이터베이스 저장 중 에러 발생 시 메시지를 반환
        }
    }

    @GetMapping("dataList")
    public List<Map<String, Object>> dataList(@RequestParam Map<String, Object> pmap) {
        logger.info("dataList");
        List<Map<String, Object>> result = crawlingService.dataList(pmap);
        logger.info(pmap.toString());
        return result;
    }
}
