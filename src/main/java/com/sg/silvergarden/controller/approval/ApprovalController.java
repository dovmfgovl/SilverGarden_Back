package com.sg.silvergarden.controller.approval;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.approval.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/approval/*")
public class ApprovalController {

    @Autowired
    YAMLConfiguration config;

    @Autowired
    ApprovalService approvalService;
    @GetMapping("allApprovalList")
    public String allApprovalList(String e_no){
        log.info("allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.allApprovalList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalWaitList")
    public String approvalWaitList(String e_no){
        log.info("approvalWaitList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalWaitList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalCompleteList")
    public String approvalCompleteList(String e_no){
        log.info("approvalCompleteList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalCompleteList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalDenyList")
    public String approvalDenyList(String e_no){
        log.info("approvalDenyList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalDenyList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("approvalProgressList")
    public String approvalProgressList(String e_no){
        log.info("approvalProgressList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalProgressList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }
    @GetMapping("approvalTempList")
    public String approvalTempList(String e_no){
        log.info("approvalTempList");
        List<Map<String, Object>> dList = null;
        dList = approvalService.approvalTempList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @GetMapping("getDeptData")
    public String getDeptDate(){
        log.info("getDeptData");
        List<Map<String, Object>> dList = null;
        dList = approvalService.getDeptData();
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }
    @GetMapping("getApprovalDocCount")
    public String getApprovalDocCount(String e_no) {
        log.info("ApprovalController: getApprovalDocCount");
        Map<String, Object> rmap = null;
        rmap = approvalService.getApprovalDocCount(e_no);
        Gson g = new Gson();
        String temp = g.toJson(rmap);
        return temp;
    }

    @GetMapping("getApprovalDetail")
    public String getApprovalDetail(int d_no){
        log.info("getApprovalDetail");
        List<Map<String, Object>> dList = null;
        dList = approvalService.getApprovalDetail(d_no);
        Gson g = new Gson();
        String temp = g.toJson(dList);
        return temp;
    }

    @PostMapping("approvalInsert")
    public String approvalInsert(@RequestParam Map<String, Object> pmap, @RequestParam(name="files", required = false) MultipartFile[] files){
        int result = -1;
        List<Map<String, Object>> fileList = new ArrayList<>();
        if(files != null){//파일이 있는 경우
            for(MultipartFile file : files){
                Map<String, Object> dmap = new HashMap<>();
                String originalFilename = file.getOriginalFilename();
                String uploadFilename = getCurrentTimeMillisFormat() + "_" + FilenameUtils.getName(originalFilename);
                File upFile = new File(config.getUploadPath(), uploadFilename);//지정된 경로에 파일저장
                try {
                    file.transferTo(upFile);
                    dmap.put("e_no", pmap.get("e_no"));
                    dmap.put("ap_filepath", config.getUploadPath());
                    dmap.put("ap_fileorigin", originalFilename);
                    dmap.put("ap_filename", uploadFilename);
                    fileList.add(dmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            pmap.put("fileList", fileList);//맵에 파일리스트를 추가해줌
        }
        log.info(pmap.toString());
        result = approvalService.approvalInsert(pmap);
        return result == 0 || result == -1 ?"error":"ok";//결과값이 -1 혹은 0이면 에러를 반환
    }

    @PutMapping("passOrDeny")
    public String passOrDeny(@RequestBody Map<String, Object> pmap){
        log.info("passOrDeny");
        log.info(pmap.toString());
        int result = -1;
        result=approvalService.passOrDeny(pmap);
        log.info(String.valueOf(result));
        return result == 0 || result == -1 ?"error":"ok";
    }

    @DeleteMapping("approvalDelete")
    public String approvalDelete(int d_no){
        log.info("approvalDelete");
        int result = -1;
        result=approvalService.approvalDelete(d_no);
        log.info(String.valueOf(result));
        return result == 0 || result == -1 ?"error":"ok";
    }

    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
    }
}
