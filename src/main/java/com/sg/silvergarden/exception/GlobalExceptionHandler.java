package com.sg.silvergarden.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.NoSuchFileException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {

    //@Autowired
    ExceptionHandlerService exceptionHandlerService;

    private Map<String, Object> getBody(String message){//클라이언트로 보낼 body를 가져오는 메서드
        Map<String, Object> body = new HashMap<>();
        body.put("status", "500");
        body.put("error", "Internal Server Error");
        body.put("message", "에러가 발생했습니다. 관리자에게 문의하세요");
        return body;
    }

    private String getStackTraceAsString(Exception e) {//예외시 발생한 stack trace를 문자열로 반환
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
    @ExceptionHandler({Exception.class})
    private ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest request){
        log.info("Exception");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage() == null ? "예외메시지 없음" : ex.getMessage();
        Map<String, Object> exMap = new HashMap<>();
        exMap.put("req_url", request.getRequestURL().toString());
        exMap.put("req_method", request.getMethod());
        exMap.put("ex_message", message);
        exMap.put("ex_full", getStackTraceAsString(ex));
        exMap.put("ex_type", ex.getClass().getSimpleName());
        log.info(exMap.toString());
        exceptionHandlerService.exceptionMessageInsert(exMap);
        return new ResponseEntity<>(getBody(message), status);
    }

    //@ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Map<String, Object>> handleNullPointerException(Exception ex, HttpServletRequest request){
        log.info("nullPointerException");
        String requestURL = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String message = ex.getMessage() == null ? "예외메시지 없음" : ex.getMessage();
        Map<String, Object> exMap = new HashMap<>();
        exMap.put("req_url", requestURL);
        exMap.put("req_method", requestMethod);
        exMap.put("ex_message", message);
        exMap.put("ex_full", getStackTraceAsString(ex));
        exMap.put("ex_type", "NullPointerException");
        //exceptionHandlerService.exceptionMessageInsert(exMap);
        return new ResponseEntity<>(getBody(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler({
//            MethodArgumentNotValidException.class,
//            HttpMessageNotReadableException.class,
//            MissingServletRequestParameterException.class,
//            MethodArgumentTypeMismatchException.class,
//            IllegalArgumentException.class
//    })
    public ResponseEntity<Map<String, Object>> handleBadRequestException(RuntimeException ex, HttpServletRequest request){
        log.info("RuntimeException");
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@ExceptionHandler({ClassCastException.class})
    public ResponseEntity<Map<String, Object>> handleClassCastException(Exception ex, HttpServletRequest request){
        log.info("ClassCastException");
        String requestURL = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String message = ex.getMessage() == null ? "예외메시지 없음" : ex.getMessage();
        Map<String, Object> exMap = new HashMap<>();
        exMap.put("req_url", requestURL);
        exMap.put("req_method", requestMethod);
        exMap.put("ex_message", message);
        exMap.put("ex_full", getStackTraceAsString(ex));
        exMap.put("ex_type", "ClassCastException");
        //exceptionHandlerService.exceptionMessageInsert(exMap);
        return new ResponseEntity<>(getBody(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@ExceptionHandler({NoSuchFileException.class})
    public ResponseEntity<Map<String, Object>> handleNoSuchFileException(Exception ex, HttpServletRequest request){
        log.info("NoSuchFileException");
        String requestURL = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String message = "파일을 찾을 수 없습니다.";
        Map<String, Object> exMap = new HashMap<>();
        exMap.put("req_url", requestURL);
        exMap.put("req_method", requestMethod);
        exMap.put("ex_message", message);
        exMap.put("ex_full", getStackTraceAsString(ex));
        exMap.put("ex_type", "IOException");
        //exceptionHandlerService.exceptionMessageInsert(exMap);
        return new ResponseEntity<>(getBody(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
