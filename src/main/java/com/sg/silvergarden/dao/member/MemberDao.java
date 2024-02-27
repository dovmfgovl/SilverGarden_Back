package com.sg.silvergarden.dao.member;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class MemberDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> memberList(Map<String, Object> mMap) {
        List<Map<String, Object>> mlist = null;
        mlist = sqlSessionTemplate.selectList("memberList",mMap);
        return mlist;
    }

    public int memberDelete(Map<String, Object> mMap) {
        int result = 0;
        result = sqlSessionTemplate.delete("memberDelete", mMap);
        return result;
    }

    public int memberUpdate(Map<String, Object> mMap) {
        int result = 0;
        result = sqlSessionTemplate.update("memberUpdate", mMap);
        return result;
    }

    public int memberInsert(Map<String, Object> mMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("memberInsert", mMap);
        return result;
    }

    public int counselInsert(Map<String, Object> cMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("counselInsert", cMap);
        return result;
    }

    public int counselUpdate(Map<String, Object> cMap) {
        int result = 0;
        result = sqlSessionTemplate.update("counselUpdate", cMap);
        return result;
    }

    public int counselDelete(Map<String, Object> cMap) {
        log.info("counselDelete-dao");
        int result=0;
        result = sqlSessionTemplate.delete("counselDelete", cMap);
        return result;
    }

    public List<Map<String, Object>> counselList(Map<String, Object> cMap) {
        List<Map<String, Object>> clist = null;
        clist = sqlSessionTemplate.selectList("counselList",cMap);
        return clist;
    }

    public List<Map<String, Object>> shuttleList(Map<String, Object> sMap) {
        List<Map<String, Object>> sList = null;
        sList = sqlSessionTemplate.selectList("shuttleList",sMap);
        return sList;
    }

    public int shuttleDelete(Map<String, Object> sMap) {
        int result = 0;
        result = sqlSessionTemplate.delete("shuttleDelete", sMap);
        return result;
    }

    public int shuttleUpdate(Map<String, Object> sMap) {
        int result = 0;
        result = sqlSessionTemplate.update("shuttleUpdate", sMap);
        return result;
    }

    public int shuttleInsert(Map<String, Object> sMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("shuttleInsert", sMap);
        return result;
    }
}
