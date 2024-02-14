package com.sg.silvergarden.service.program;

import com.sg.silvergarden.dao.program.ProgramDao;
import com.sg.silvergarden.vo.program.ProgramVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService {
    Logger logger = LoggerFactory.getLogger(ProgramService.class);
    @Autowired
    ProgramDao programDao;
    @GetMapping("test")

    public String test(){
        logger.info("AngelController");
        return null;
    }

    public List<Map<String, Object>> pgList(Map<String, Object> pmap) {
        logger.info("AngelLogic-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = programDao.pgList(pmap);
        return pgList;
    }

    public int pgInsert(Map<String, Object> pmap) {
        logger.info("AngelLogic-pgInsert");
        int result = 0;
        result = programDao.pgInsert(pmap);
        return result;
    }

    public int pgDelete(Map<String, Object> pmap) {
        logger.info("AngelLogic-pgDelete");
        int result = 0;
        result = programDao.pgDelete(pmap);
        return result;
    }

    public List<ProgramVO> scheduleList() {
        logger.info("AngelLogic-scheduleList");
        List<ProgramVO> calList = null;
        calList = programDao.scheduleList();
        return calList;
    }

    public int pgUpdate(Map<String, Object> pmap) {
        logger.info("AngelLogic-pgUpdate");
        int result = programDao.pgUpdate(pmap);
        return result;
    }
}
