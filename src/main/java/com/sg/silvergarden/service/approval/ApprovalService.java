package com.sg.silvergarden.service.approval;

import com.google.gson.Gson;
import com.sg.silvergarden.dao.approval.ApprovalDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApprovalService {
    @Autowired
    ApprovalDao approvalDao;
    public List<Map<String, Object>> allApprovalList(Map<String, Object> pmap) throws Exception{
        log.info("approvalService: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.allApprovalList(pmap);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> getDeptData() throws Exception{
        log.info("approvalService: getDeptData");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.getDeptData();
        return dList;
    }

    public Map<String, Object> getApprovalDocCount(String e_no) throws Exception{
        log.info("ApprovalDao: getApprovalDocCount");
        Map<String, Object> rmap = null;
        rmap = approvalDao.getApprovalDocCount(e_no);
        return rmap;
    }

    public List<Map<String, Object>> getApprovalDetail(int d_no) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = approvalDao.getApprovalDetail(d_no);
        log.info(dList.toString());
        return dList;
    }

    @Transactional
    public int approvalInsert(Map<String, Object> pmap) throws Exception{//결재문서, 결재이력, 첨부파일을 한번에 생성하는 메서드
        int result = -1;
        Gson g = new Gson();
        List<Map<String, Object>> line = null;
        if(pmap.containsKey("fileList")){
            log.info("파일 있음");
            result = approvalDao.approvalInsert(pmap);
            List<Map<String, Object>> fileList = (List)pmap.get("fileList");
            for(Map<String, Object> map : fileList){//파일리스트에 d_no를 넣어줌
                map.put("d_no", pmap.get("d_no"));
            }
            result = approvalDao.fileUpload(fileList);
            if(pmap.containsKey("line")){//결재라인이 있는 경우에만 결재이력을 생성
                line = g.fromJson(pmap.get("line").toString(), List.class);
                for(Map<String, Object> map : line){//결재라인리스트에 d_no를 넣어줌
                    map.put("d_no", pmap.get("d_no"));
                }
                result = approvalDao.approvalHistoryInsert(line);
            }
        }else{
            log.info("파일 없음");
            result = approvalDao.approvalInsert(pmap);
            if(pmap.containsKey("line")){//결재라인이 있는 경우에만 결재이력을 생성
                line = g.fromJson(pmap.get("line").toString(), List.class);
                log.info(line.toString());
                for(Map<String, Object> map : line){//결재라인리스트에 d_no를 넣어줌
                    map.put("d_no", pmap.get("d_no"));
                }
                result = approvalDao.approvalHistoryInsert(line);
            }
        }
        log.info(String.valueOf(result));
        return result;
    }

    public int vacationDateInsert(Map<String, Object> pmap) throws Exception{
        int result = -1;
        Gson g = new Gson();
        Map<String, Object> content = g.fromJson(pmap.get("d_content").toString(), Map.class);//휴가정보
        List<Map<String, Object>> dateList = new ArrayList<>();//휴가일자 리스트
        int days = Integer.parseInt(content.get("totalDate").toString());//휴가 일수
        LocalDate startDate = LocalDate.parse(content.get("startDate").toString());//시작일자

        for(int i = 0; i< days; i++){//시작일자부터 휴가일수 만큼 반복
            LocalDate date = startDate.plusDays(i);
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.toString());
            map.put("e_no", pmap.get("e_no"));
            map.put("at_status", "휴가");
            dateList.add(map);
        }
        log.info(dateList.toString());
        result = approvalDao.vacationDateInsert(dateList);
        return result;
    }

    @Transactional
    public int passOrDeny(Map<String, Object> pmap) throws Exception{
        int result = -1;
        int finalApprovalLev = approvalDao.getFinalApprovalLevel((int)pmap.get("d_no"));//현재 문서의 최종결재단계를 얻어옴
        log.info(pmap.toString());
        if("반려".equals(pmap.get("ap_result"))){//결재결과가 반려라면
            approvalDao.passOrDeny(pmap);//결재이력을 반려로 업데이트
            pmap.put("d_status", "반려");
            result = approvalDao.statusUpdate(pmap);//결재문서상태를 반려로 바꿈
        }else{
            if(finalApprovalLev == (int)pmap.get("ap_lev") && "결재".equals(pmap.get("ap_category"))){
                //현재 단계가 최종결재이면서 카테고리가 '결재'라면
                approvalDao.passOrDeny(pmap);
                pmap.put("d_status", "종결");
                result = approvalDao.statusUpdate(pmap);
                if("휴가신청서".equals(pmap.get("d_category"))){
                    result = vacationDateInsert(pmap);
                }
            }
            else{//죄종결재자가 아니라면
                approvalDao.passOrDeny(pmap);
                pmap.put("d_status", "진행중");
                result = approvalDao.statusUpdate(pmap);
            }
        }
        return result;
    }

    public List<Map<String, Object>> approvalWaitList(Map<String, Object> pmap) throws Exception{
        log.info("approvalService: approvalWaitList");
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalWaitList(pmap);
        return dList;
    }

    public List<Map<String, Object>> approvalCompleteList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalCompleteList(pmap);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalDenyList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalDenyList(pmap);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalProgressList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalProgressList(pmap);
        log.info(dList.toString());
        return dList;
    }

    public List<Map<String, Object>> approvalTempList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = approvalDao.approvalTempList(pmap);
        log.info(dList.toString());
        return dList;
    }

    public int approvalDelete(int d_no) throws Exception{
        int result = -1;
        result =approvalDao.approvalDelete(d_no);
        return result;
    }

    public int approvalWithdrawal(int d_no) throws Exception{
        int result = -1;
        Map<String, Object> map = new HashMap<>();
        map.put("d_no", d_no);
        map.put("d_status", "임시저장");
        result =approvalDao.statusUpdate(map);
        return result;
    }

}
