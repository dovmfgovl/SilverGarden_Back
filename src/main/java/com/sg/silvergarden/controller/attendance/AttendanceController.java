package com.sg.silvergarden.controller.attendance;

import com.google.gson.Gson;
import com.sg.silvergarden.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/at/*")
@RequiredArgsConstructor
public class AttendanceController {
    Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    @Autowired
    private AttendanceService attendanceService;

    // 근태 조회
    @GetMapping("atList")
    public String atList(@RequestParam Map<String, Object> atMap) {
        logger.info("atList");
        List<Map<String, Object>> aList = null;
        aList = attendanceService.atList(atMap);
        Gson g = new Gson();
        String temp = g.toJson(aList);
        return temp;
    }

    // 근태 출근 insert
    @PostMapping("atInsert")
    public String atInsert(@RequestBody Map<String, Object> atMap) {
        logger.info("atInsert");
        logger.info(atMap.toString());
        int result = 0;
        result = attendanceService.atInsert(atMap);
        return String.valueOf(result);
    }

    // 근태 퇴근 update
    @PutMapping("atUpdate")
    public String atUpdate(@RequestBody Map<String, Object> atMap) {
        logger.info("atUpdate");
        logger.info(atMap.toString());
        int result = 0;
        result = attendanceService.atUpdate(atMap);
        return String.valueOf(result);
    }

    // 관리자 - 근태 상태 update (시간은 변경되지 않고 AT_STATUS만 변경)
    @PutMapping("adminAtUpdate")
    public String adminAtUpdate(@RequestBody Map<String, Object> atMap) {
        logger.info("adminAtUpdate");
        logger.info(atMap.toString());
        int result = attendanceService.adminAtUpdate(atMap);
        return String.valueOf(result);
    }
}
