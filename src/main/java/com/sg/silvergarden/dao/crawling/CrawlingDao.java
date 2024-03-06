package com.sg.silvergarden.dao.crawling;

import com.sg.silvergarden.controller.crawling.CrawlingController;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public void dataInsert(List<Map<String, Object>> insertData) {
        logger.info(insertData.toString());
        logger.info("///////////CrawlingService-dataInsert - " + java.time.LocalDateTime.now());
        sqlSessionTemplate.insert("insertCrawledData", insertData);
    }

//    public void dataInsert(List<Map<String, Object>> insertData) {
//        logger.info(insertData.toString());
//        logger.info("///////////CrawlingService-dataInsert - " + java.time.LocalDateTime.now());
//
//        for (Map<String, Object> item : insertData) {
//            // 중복 여부를 확인하는 쿼리 실행
//            int duplicateCount = sqlSessionTemplate.selectOne("checkDuplicateCrawledData", item.get("title"));
//
//            // 중복된 데이터가 아니면 저장
//            if (duplicateCount == 0) {
//                sqlSessionTemplate.insert("insertCrawledData", item);
//            }
//        }
//    }
}
