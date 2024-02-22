package com.sg.silvergarden.service.program;

import com.sg.silvergarden.dao.program.ProgramDao2;
import com.sg.silvergarden.vo.programcal.ProgramSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService2 {
    Logger logger = LoggerFactory.getLogger(ProgramService2.class);
    @Autowired
    ProgramDao2 programDao;

    public List<Map<String, Object>> pgList(Map<String, Object> pmap) {
        logger.info("ProgramService2-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = programDao.pgList(pmap);
        return pgList;
    }

    public int pgInsert(Map<String, Object> pmap) {
        logger.info("ProgramService2-pgInsert");
        int result = 0;
        result = programDao.pgInsert(pmap);
        // 삽입 후에는 pg_no가 설정되어 있을 것으로 가정
        return result;
    }

    public int pgDelete(Map<String, Object> pmap) {
        logger.info("ProgramService2-pgDelete");
        int result = 0;
        result = programDao.pgDelete(pmap);
        return result;
    }

    public List<ProgramSchedule> scheduleList() {
        logger.info("ProgramService2-scheduleList");
        List<ProgramSchedule> calList = null;
        calList = programDao.scheduleList();
        return calList;
    }

    public int pgUpdate(Map<String, Object> pmap) {
        logger.info("ProgramService2-pgUpdate");
        int result = programDao.pgUpdate(pmap);
        return result;
    }

    public int pgCalendarInsert(BigDecimal pgNo) {
        logger.info("ProgramService2-pgCalendarInsert");
        int result = programDao.pgCalendarInsert(pgNo);
        return result;
    }
    public int pgCalendarUpdate(BigDecimal pgNo) {
        logger.info("ProgramService2-executeSecondQuery");
        int result = programDao.pgCalendarUpdate(pgNo);
        return result;
    }

    public int pgCalendarAllDelete(BigDecimal pgNo) {
        logger.info("ProgramService2-pgCalendarAllDelete");
        int result = programDao.pgCalendarAllDelete(pgNo);
        return result;
    }
}
