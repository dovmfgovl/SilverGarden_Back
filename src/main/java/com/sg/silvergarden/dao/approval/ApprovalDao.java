package com.sg.silvergarden.dao.approval;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ApprovalDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> allApprovalList(String e_no) {
        log.info("ApprovalDao: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("allApprovalList",e_no);
        return dList;
    }

    public List<Map<String, Object>> getDeptData() {
        log.info("ApprovalDao: getDeptData");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("getDeptData");
        return dList;
    }
}
