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

//    @Transactional //유니크키값을 가진 데이터 중복처리 예정
//    public void dataInsert(List<Map<String, Object>> insertData) {
//        for (Map<String, Object> crawledData : insertData) {
//            try {
//                sqlSessionTemplate.insert("insertCrawledData", crawledData);
//            } catch (DuplicateKeyException e) {
//                logger.info("중복된 데이터가 있어 삽입하지 않습니다. 타이틀: {}", crawledData.get("title"));
//                // 중복이 발생하면 로그를 남기고 계속 진행
//            } catch (Exception e) {
//                logger.error("데이터 삽입 중 에러 발생", e);
//                // 기타 예외 처리 로직 추가
//            }
//        }
//    }
}
