package com.sg.silvergarden.dao.empinfo;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmpInfoDao {
    Logger logger = LoggerFactory.getLogger(EmpInfoDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> empList(Map<String, Object> eMap) {
        logger.info("empList");
        List<Map<String, Object>> eList = sqlSessionTemplate.selectList("empList", eMap);
        logger.info(eList.toString());
        return eList;
    }

    public int empUpdate(Map<String, Object> eMap) {
        logger.info("empUpdate");
        int result = 0;
        try {
            result = sqlSessionTemplate.update("empInfoUpdate", eMap);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }

    public int empDelete(int e_no) {
        logger.info("empDelete");
        int result = 0;
        try {
            result = sqlSessionTemplate.delete("empDelete", e_no);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }
}
