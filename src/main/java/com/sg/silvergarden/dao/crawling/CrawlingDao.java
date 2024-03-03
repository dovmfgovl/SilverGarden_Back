package com.sg.silvergarden.dao.crawling;

import com.sg.silvergarden.controller.crawling.CrawlingController;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CrawlingDao {
    Logger logger = LoggerFactory.getLogger(CrawlingController.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    public List<Map<String, Object>> dataList(Map<String, Object> pmap) {
        return sqlSessionTemplate.selectList("getAllCrawledData",pmap);
    }
    public void dataInsert(List<Map<String, Object>> pList) {
        logger.info("///////////CrawlingService-dataInsert - " + java.time.LocalDateTime.now());
        sqlSessionTemplate.insert("insertCrawledData", pList);
    }
}