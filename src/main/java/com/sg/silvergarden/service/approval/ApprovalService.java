package com.sg.silvergarden.service.approval;

import com.sg.silvergarden.dao.approval.ApprovalDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApprovalService {
    @Autowired
    ApprovalDao approvalDao;
    public List<Map<String, Object>> allApprovalList(String e_no) {
        log.info("approvalService: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.allApprovalList(e_no);
        log.info(dList.toString());
        return dList;
    }
}
