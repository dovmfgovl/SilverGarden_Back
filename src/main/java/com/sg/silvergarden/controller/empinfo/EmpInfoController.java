package com.sg.silvergarden.controller.empinfo;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.empinfo.EmpInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/emp/*")
@RequiredArgsConstructor
public class EmpInfoController {
    Logger logger = LoggerFactory.getLogger(EmpInfoController.class);
    @Autowired
    private EmpInfoService empInfoService;

    @Autowired
    YAMLConfiguration config;

    // 직원 조회(인적사항, 학력)
    @GetMapping("empList")
    public String empList(@RequestParam Map<String, Object> eMap) {
        logger.info("empList");
        List<Map<String, Object>> eList = null;
        eList = empInfoService.empList(eMap);
        Gson g = new Gson();
        String temp = g.toJson(eList);
        return temp;
    }

    // 직원 경력 조회
    @GetMapping("experienceList")
    public String experienceList(@RequestParam Map<String, Object> eMap) {
        logger.info("experienceList");
        List<Map<String, Object>> eList = null;
        eList = empInfoService.experienceList(eMap);
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
    @PutMapping("empUpdate")
    public String empUpdate(@RequestBody Map<String, Object> eMap, @RequestParam(name="files", required=false) MultipartFile[] files) {
        logger.info("empUpdate");
        logger.info(eMap.toString());
        List<Map<String, Object>> list = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                Map<String, Object> nmap = new HashMap<>();
                String originalFilename = file.getOriginalFilename();
                String uploadFilename = getCurrentTimeMillisFormat() + "_" + FilenameUtils.getName(originalFilename);
                File upFile = new File(config.getUploadPath(), uploadFilename);//지정된 경로에 파일저장
                try {
                    file.transferTo(upFile);
                    nmap.put("e_no", eMap.get("e_no"));
                    nmap.put("n_filepath", config.getUploadPath());
                    nmap.put("n_fileorigin", originalFilename);
                    nmap.put("n_filename", uploadFilename);
                    list.add(nmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            eMap.put("list", list); // 맵에 파일리스트 추가
        }
        logger.info(eMap.toString());
        int result = 0;
        result = empInfoService.empUpdate(eMap);
        return String.valueOf(result);
    }

    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
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

    // 직원 학력 수정
    @PutMapping("empEduUpdate")
    public String empEduUpdate(@RequestBody Map<String, Object> eMap) {
        logger.info("empEduUpdate");
        logger.info(eMap.toString());
        int result = 0;
        result = empInfoService.empEduUpdate(eMap);
        return String.valueOf(result);
    }

    // 직원 경력 insert
    @PostMapping("experienceInsert")
    public String experienceInsert(@RequestBody Map<String, Object> eMap) {
        logger.info("experienceInsert");
        logger.info("eMap: {}", eMap);
        int result = 0;
        result = empInfoService.experienceInsert(eMap);
        return String.valueOf(result);
    }

    // 직원 경력 delete
    @DeleteMapping("experienceDelete")
    public String experienceDelete(int exp_no) {
        logger.info("experienceDelete");
        int result = 0;
        result = empInfoService.experienceDelete(exp_no);
        Gson g = new Gson();
        String temp = g.toJson(result);
        return temp;
    }

    // 직원 자격증 insert
    @PostMapping("certiInsert")
    public String certiInsert(@RequestBody Map<String, Object> eMap) {
        logger.info("certiInsert");
        logger.info("eMap: {}", eMap);
        int result = 0;
        result = empInfoService.certiInsert(eMap);
        return String.valueOf(result);
    }
}
