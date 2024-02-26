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

    public List<Map<String, Object>> experienceList(Map<String, Object> eMap) {
        logger.info("experienceList");
        List<Map<String, Object>> eList = null;
        eList = empInfoDao.experienceList(eMap);
        return eList;
    }

    public List<Map<String, Object>> empDetail(Map<String, Object> eMap) {
        logger.info("empDetail");
        List<Map<String, Object>> eList = null;
        eList = empInfoDao.empList(eMap);
        return eList;
    }

    @Transactional
    public int empUpdate(Map<String, Object> eMap) {
        logger.info("empUpdate");
        int result = 0;
        if(eMap.containsKey("list")){//파일이 있는 경우 empUpdate를 먼저하고 시퀀스 값을 받아옴
            List<Map<String, Object>> flist = (List)eMap.get("list");
            empInfoDao.empUpdate(eMap);
            for(Map<String, Object> map : flist){
                map.put("E_NO", eMap.get("E_NO"));
            }
            result = empInfoDao.fileUpload(flist);
        }else{
            result = empInfoDao.empUpdate(eMap);
        }
        return result;
    }

    public int empDelete(int e_no) {
        logger.info("empDelete");
        int result = 0;
        result = empInfoDao.empDelete(e_no);
        return result;
    }

    @Transactional
    public int empEduUpdate(Map<String, Object> eMap) {
        logger.info("empEduUpdate");
        logger.info(eMap.toString());
        int result = 0;
        result = empInfoDao.empEduUpdate(eMap);
        return result;
    }

    public int experienceInsert(Map<String, Object> eMap) {
        logger.info("experienceInsert");
        int result = 0;
        result = empInfoDao.experienceInsert(eMap);
        return result;
    }

    public int experienceDelete(int exp_no) {
        logger.info("experienceDelete");
        int result = 0;
        result = empInfoDao.experienceDelete(exp_no);
        return result;
    }

    public int certiInsert(Map<String, Object> eMap) {
        logger.info("certiInset");
        int result = 0;
        result = empInfoDao.certiInsert(eMap);
        return result;
    }
}
