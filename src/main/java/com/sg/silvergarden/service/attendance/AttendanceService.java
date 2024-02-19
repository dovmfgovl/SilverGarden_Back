package com.sg.silvergarden.service.attendance;

import com.sg.silvergarden.dao.attendance.AttendanceDao;
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
public class AttendanceService {
    Logger logger = LoggerFactory.getLogger(AttendanceService.class);
    @Autowired
    private AttendanceDao attendanceDao;

    public List<Map<String, Object>> atList(Map<String, Object> atMap) {
        logger.info("atList");
        List<Map<String, Object>> aList = null;
        aList = attendanceDao.atList(atMap);
        return aList;
    }

    public int atInsert(Map<String, Object> atMap) {
        logger.info("atInsert");
        int result = 0;
        result = attendanceDao.atInsert(atMap);
        return result;
    }

    public int atUpdate(Map<String, Object> atMap) {
        logger.info("atUpdate");
        int result = 0;
        result = attendanceDao.atUpdate(atMap);
        return result;
    }

    public int adminAtUpdate(Map<String, Object> atMap) {
        logger.info("adminAtUpdate");
        int result = attendanceDao.adminAtUpdate(atMap);
        return result;
    }
}
