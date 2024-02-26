package com.sg.silvergarden.service.schedule;

import com.sg.silvergarden.dao.dept.DeptDao;
import com.sg.silvergarden.dao.schedule.ScheduleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ScheduleService {

    @Autowired
    ScheduleDao scheduleDao = null;

    public List<Map<String, Object>> scheduleList(Map<String, Object> pmap) {
        log.info("scheduleList-service 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = scheduleDao.scheduleList(pmap);

        return list;
    }

    public void scheduleInsert(Map<String, Object> pmap) {
        log.info("scheduleInsert-service 호출");
        scheduleDao.scheduleInsert(pmap);
    }

    public void scheduleUpdate(Map<String, Object> pmap) {
        log.info(pmap);
        log.info("scheduleUpdate-service 호출");
        scheduleDao.scheduleUpdate(pmap);
    }

    public void scheduleDelete(Map<String, Object> pmap) {
        log.info(pmap);
        log.info("scheduleDelete-service 호출");
        scheduleDao.scheduleDelete(pmap);
    }
}
