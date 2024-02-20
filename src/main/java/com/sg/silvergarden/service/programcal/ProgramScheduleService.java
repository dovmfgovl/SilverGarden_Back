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

    public List<Map<String, Object>> getProgramSchedule(Map<String, Object> pmap) {
        return programScheduleDao.getProgramSchedule(pmap);
    }

    public int addProgramSchedule(Map<String, Object> pmap) {
        return programScheduleDao.addProgramSchedule(pmap);
    }

    public int deleteProgramSchedule(Map<String, Object> psNo2) {
        return programScheduleDao.deleteProgramSchedule(psNo2);
    }

    public int updateProgramSchedule(Map<String, Object> pmap) {
        return programScheduleDao.updateProgramSchedule(pmap);
    }
}