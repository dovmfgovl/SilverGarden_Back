package com.sg.silvergarden.service.programcal;

import com.sg.silvergarden.dao.programcal.ProgramScheduleDao;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
@Service
public class ProgramScheduleService {
    Logger logger = LoggerFactory.getLogger(ProgramScheduleService.class);

    @Autowired
    ProgramScheduleDao programScheduleDao;

    public List<Map<String, Object>> pgCalendarList(Map<String, Object> pmap) {
        logger.info("ProgramScheduleService - pgCalendarList");
        return programScheduleDao.pgCalendarList(pmap);
    }

    public int pgCalendarAdd(Map<String, Object> pmap) {
        logger.info("ProgramScheduleService - pgCalendarAdd");
        return programScheduleDao.pgCalendarAdd(pmap);
    }

    public int pgCalendarDelete(Map<String, Object> psNo) {
        logger.info("ProgramScheduleService - pgCalendarDelete");
        return programScheduleDao.pgCalendarDelete(psNo);
    }

    public int pgCalendarUpdate(Map<String, Object> pmap) {
        logger.info("ProgramScheduleService - pgCalendarUpdate");
        return programScheduleDao.pgCalendarUpdate(pmap);
    }
}