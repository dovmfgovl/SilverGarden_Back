package com.sg.silvergarden.exception;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ExceptionHandlerDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public int exceptionMessageInsert(Map<String, Object> exMap) {
        return sqlSessionTemplate.insert("exceptionMessageInsert", exMap);
    }
}
