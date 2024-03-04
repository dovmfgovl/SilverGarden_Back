package com.sg.silvergarden.dao.approval;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ApprovalDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> allApprovalList(Map<String, Object> pmap) throws Exception{
        log.info("ApprovalDao: allApprovalList");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("allApprovalList",pmap);
        return dList;
    }

    public List<Map<String, Object>> getDeptData() throws Exception{
        log.info("ApprovalDao: getDeptData");
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("getDeptData");
        return dList;
    }
    public Map<String, Object> getApprovalDocCount(String e_no) throws Exception{
        log.info("ApprovalDao: getApprovalDocCount");
        Map<String, Object> rmap = null;
        rmap = sqlSessionTemplate.selectOne("getApprovalDocCount", e_no);
        return rmap;
    }

    public List<Map<String, Object>> getApprovalDetail(int d_no) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("getApprovalDetail",d_no);
        log.info(dList.toString());
        return dList;
    }

    public int approvalInsert(Map<String, Object> pmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.insert("approvalInsert", pmap);
        return result;
    }

    public int fileUpload(List<Map<String, Object>> fileList) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.insert("approvalFileUpload", fileList);
        return result;
    }

    public int approvalHistoryInsert(List<Map<String, Object>> line) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.insert("approvalHistoryInsert", line);
        return result;
    }

    public List<Map<String, Object>> approvalWaitList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalWaitList",pmap);
        return dList;
    }

    public int passOrDeny(Map<String, Object> pmap) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.update("passOrDeny",pmap);
        return result;
    }

    public int getFinalApprovalLevel(int d_no) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.selectOne("getFinalApprovalLevel", d_no);
        return result;
    }

    public int statusUpdate(Map<String, Object> pmap) throws Exception{
        int result = -1;
        result =sqlSessionTemplate.update("statusUpdate", pmap);
        return result;
    }

    public int approvalDelete(int d_no) throws Exception{
        int result = -1;
        result =sqlSessionTemplate.delete("approvalDelete", d_no);
        return result;
    }

    public List<Map<String, Object>> approvalCompleteList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalCompleteList",pmap);
        return dList;
    }

    public List<Map<String, Object>> approvalDenyList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalDenyList",pmap);
        return dList;
    }

    public List<Map<String, Object>> approvalProgressList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalProgressList",pmap);
        return dList;
    }

    public List<Map<String, Object>> approvalTempList(Map<String, Object> pmap) throws Exception{
        List<Map<String, Object>> dList = null;
        dList = sqlSessionTemplate.selectList("approvalTempList",pmap);
        return dList;
    }

    public int vacationDateInsert(List<Map<String, Object>> dateList) throws Exception{
        int result = -1;
        result = sqlSessionTemplate.insert("vacationDateInsert", dateList);
        return result;
    }
}
