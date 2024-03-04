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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {
    Logger logger = LoggerFactory.getLogger(CrawlingController.class);

    @Autowired
    private CrawlingDao crawlingDao;

    public List<Map<String, Object>> dataList(Map<String, Object> pmap) {
        logger.info("ProgramScheduleService - pgCalendarList");
        return crawlingDao.dataList(pmap);
    }

    public void dataInsert(String data) {
        try {
            logger.info(data);
            Gson gson = new Gson();
            List<Map<String, Object>> insertData = null;
            insertData = gson.fromJson(data, List.class);
            logger.info(insertData.toString());
            crawlingDao.dataInsert(insertData);
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 로그 출력 및 필요한 예외 처리 추가
        }
    }
}
