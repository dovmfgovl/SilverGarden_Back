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
        int result = -1;
        result = sqlSessionTemplate.delete("memberDelete", mMap);
        return result;
    }

    public int memberUpdate(Map<String, Object> mMap) {
        int result = -1;
        result = sqlSessionTemplate.delete("memberUpdate", mMap);
        return result;
    }

    public int memberInsert(Map<String, Object> mMap) {
        int result = -1;
        result = sqlSessionTemplate.delete("memberInsert", mMap);
        return result;
    }
}
