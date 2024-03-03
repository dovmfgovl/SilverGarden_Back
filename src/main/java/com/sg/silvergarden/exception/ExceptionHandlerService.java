package com.sg.silvergarden.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExceptionHandlerService {
    @Autowired
    ExceptionHandlerDao exceptionHandlerDao;
    public int exceptionMessageInsert(Map<String, Object> exMap) {
        return exceptionHandlerDao.exceptionMessageInsert(exMap);
    }
}
