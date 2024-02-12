package com.sg.silvergarden.controller.empinfo;

import com.google.gson.Gson;
import com.sg.silvergarden.service.empinfo.EmpInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp/*")
@RequiredArgsConstructor
public class EmpInfoController {
    Logger logger = LoggerFactory.getLogger(EmpInfoController.class);
    @Autowired
    private EmpInfoService empInfoService;

    // 직원 조회
    @GetMapping("empList")
    public String empList(@RequestParam Map<String, Object> eMap) {
        logger.info("empList");
        List<Map<String, Object>> eList = null;
        eList = empInfoService.empList(eMap);
        Gson g = new Gson();
        String temp = g.toJson(eList);
        return temp;
    }

    // 직원 상세조회
    @GetMapping("empDetail")
    public String empDetail(@RequestParam Map<String, Object> eMap) {
        logger.info("empDetail");
        List<Map<String, Object>> eList = null;
        eList = empInfoService.empDetail(eMap);
        Gson g = new Gson();
        String temp = g.toJson(eList);
        return temp;
    }

    // 직원 수정
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("empUpdate")
    public String empUpdate(@RequestBody Map<String, Object> eMap) {
        logger.info("empUpdate");
        logger.info(eMap.toString());
        int result = 0;
        result = empInfoService.empUpdate(eMap);
        return String.valueOf(result);
    }

    // 직원 삭제
    @GetMapping("empDelete")
    public String empDelete(int e_no) {
        logger.info("empDelete");
        int result = 0;
        result = empInfoService.empDelete(e_no);
        Gson g = new Gson();
        String temp = g.toJson(result);
        return temp;
    }
}
