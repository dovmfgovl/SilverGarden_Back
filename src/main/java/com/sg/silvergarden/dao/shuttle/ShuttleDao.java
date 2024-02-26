package com.sg.silvergarden.dao.shuttle;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ShuttleDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
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
