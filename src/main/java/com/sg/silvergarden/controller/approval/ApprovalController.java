package com.sg.silvergarden.controller.approval;

import com.google.gson.Gson;
import com.sg.silvergarden.service.approval.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/approval/*")
public class ApprovalController {

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

}
