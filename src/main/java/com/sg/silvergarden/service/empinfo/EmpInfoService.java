package com.sg.silvergarden.service.empinfo;

import com.sg.silvergarden.dao.empinfo.EmpInfoDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class EmpInfoService {
    Logger logger = LoggerFactory.getLogger(EmpInfoService.class);
    @Autowired
    private EmpInfoDao empInfoDao;

    public List<Map<String, Object>> empList(Map<String, Object> eMap) {
        logger.info("empList");
        List<Map<String, Object>> eList = null;
        eList = empInfoDao.empList(eMap);
        return eList;
    }

    public List<Map<String, Object>> empDetail(Map<String, Object> eMap) {
        logger.info("empDetail");
        List<Map<String, Object>> eList = null;
        eList = empInfoDao.empList(eMap);
        return eList;
    }

    public int empUpdate(Map<String, Object> eMap) {
        logger.info("empUpdate");
        int result = 0;
        result = empInfoDao.empUpdate(eMap);
        return result;
    }

    public int empDelete(int e_no) {
        logger.info("empDelete");
        int result = 0;
        result = empInfoDao.empDelete(e_no);
        return result;
    }
}
