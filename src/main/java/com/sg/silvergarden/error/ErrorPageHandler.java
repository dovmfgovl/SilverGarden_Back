package com.sg.silvergarden.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ErrorPageHandler{
    //@GetMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        log.info("ErrorPageHandler");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Map<String, Object> body = new HashMap<>();

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
            body.put("status", statusCode);
            body.put("error", httpStatus.getReasonPhrase());
            log.info(status.toString());//500
            log.info(httpStatus.getReasonPhrase());//Internal Server Error

            switch (httpStatus) {
                case UNAUTHORIZED:
                    body.put("message", "인증되지 않은 사용자입니다.");
                    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
                case FORBIDDEN:
                    body.put("message", "접근권한이 없습니다.");
                    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
                case BAD_REQUEST:
                    body.put("message", "잘못된 요청입니다. 요청을 올바르게 해주세요.");
                    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
                case NOT_FOUND:
                    body.put("message", "페이지를 찾을 수 없습니다.");
                    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
                default:
                    body.put("message", "서버에러입니다.");
                    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        body.put("message", "알 수 없는 에러입니다. 관리자에게 문의해주세요.");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
