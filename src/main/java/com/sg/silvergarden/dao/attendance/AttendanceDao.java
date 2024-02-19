package com.sg.silvergarden.dao.attendance;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDao {
    Logger logger = LoggerFactory.getLogger(AttendanceDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> atList(Map<String, Object> atMap) {
        logger.info("atList");
        List<Map<String, Object>> aList = sqlSessionTemplate.selectList("atList", atMap);
        logger.info(aList.toString());
        return aList;
    }

    public int atInsert(Map<String, Object> atMap) {
        logger.info("atInsert");
        logger.info(atMap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("atInsert", atMap);
        return result;
    }

    public int atUpdate(Map<String, Object> atMap) {
        logger.info("atUpdate");
        logger.info(atMap.toString());
        int result = sqlSessionTemplate.update("atUpdate", atMap);
        return result;
    }

    public int adminAtUpdate(Map<String, Object> atMap) {
        logger.info("adminAtUpdate");
        logger.info(atMap.toString());
        int result = sqlSessionTemplate.update("adminAtUpdate", atMap);
        return result;
    }
}
