package com.sg.silvergarden.controller.program;

import com.google.gson.Gson;
import com.sg.silvergarden.service.program.ProgramService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/program/*")
public class ProgramController2 {
    Logger logger = LoggerFactory.getLogger(ProgramController2.class);
    @Autowired
    ProgramService2 programService;
    @Transactional
    @PostMapping("pgInsert")
    //프로그램 생성하기 -> 성공 시 프로그램 일정 생성하기(PG_NO 가져와서 처리하기)
    public String pgInsert(@RequestBody Map<String, Object> pmap) {
        logger.info("ProgramController2-pgInsert"); //{ProgramController2-pgInsert}
        logger.info("pmap: {}", pmap);//{pmap: }
        int result = -1;
        result=programService.pgInsert(pmap);
        logger.info("pmap after pgInsert: {}", pmap);
        //{pmap after pgInsert: {PG_NAME=체육, PG_CATEGORY=신체, PG_TEACHER=철이!!!, PG_DAYSOFWEEK=목요일, PG_REPEAT_TYPE=매주, PG_START=2024-02-01T22:52, PG_END=2024-03-07T22:52, PG_CONTENT=체육활동입니다!!!, pg_no=104}}
        logger.info("result: {}", result); //{result: 1}
        // 두 번째 SQL 쿼리 실행
        if (result == 1) {
            logger.info("secontQuery");
            BigDecimal pg_no = (BigDecimal) pmap.get("pg_no");
            logger.info("pg_no:"+pg_no);//{pg_no:null}
            // pg_no가 null이 아닌 경우에만 두 번째 쿼리 실행
            if (pg_no != null) {
                int secondResult = programService.pgCalendarInsert(pg_no);
                // 두 번째 쿼리 실행 결과에 따른 처리
                if (secondResult > 0) {
                    logger.info("스케쥴 등록 성공");
                } else {
                    logger.info("스케쥴 등록 실패");
                }
            } else {
                logger.warn("pg_no null, 롤백실행-프로그램도 생성x");
            }
        }
        return String.valueOf(result);
    }
    //프로그램 업데이트하기 -> 성공 시 프로그램 일정 삭제 후 다시 Insert(해당 pg_no를 갖는 스케쥴 삭제한뒤 다시 그 이후 일정으로 insert)
    @Transactional
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "pgUpdate")
    public String pgUpdate(@RequestBody Map<String, Object> pmap) {
        logger.info("ProgramController2-pgUpdate");
        logger.info("pmap:", pmap);
        int result = 0;
        result = programService.pgUpdate(pmap);
        logger.info("pmap after pgUpdate: {}", pmap);
        logger.info("result: {}", result);
        // 두 번째 SQL 쿼리 실행
        if (result == 1) {
            logger.info("secontQuery");
            BigDecimal pg_no = (BigDecimal) pmap.get("pg_no");
            logger.info("pg_no:"+pg_no);//{pg_no:null}
            // pg_no가 null이 아닌 경우에만 두 번째 쿼리 실행
            if (pg_no != null) {
                // 프로그램 수정된 MOD_DATE 이후의 일정을 삭제
                int deleteResult = programService.pgCalendarAllDelete(pg_no);
                if (deleteResult > 0) {
                    logger.info("스케쥴 삭제 성공");
                    // 수정된 프로그램 정보를 기반으로 새로운 일정 생성
                    int secondResult = programService.pgCalendarUpdate(pg_no);
                    // 두 번째 쿼리 실행 결과에 따른 처리
                    if (secondResult > 0) {
                        logger.info("스케쥴 수정 성공");
                    } else {
                        logger.info("스케쥴 수정 실패");
                    }
                } else {
                    logger.info("스케쥴 삭제 실패");
                }
            } else {
                logger.warn("pg_no null, 롤백실행-프로그램도 생성x");
            }
        }
        return String.valueOf(result);
    }
    //프로그램 삭제하기 - 프로그램스케쥴도 일괄 삭제
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("pgDelete")
    public String pgDelete(@RequestParam Map<String, Object> pmap) {
        logger.info("ProgramController2-pgDelete");
        int result = 0;
        result = programService.pgDelete(pmap);
        return String.valueOf(result);
    }
    //전체 목록조회하기
    @GetMapping("pgList")
    public String pgList(@RequestParam Map<String, Object> pmap) {
        logger.info("ProgramController2-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = programService.pgList(pmap);
        if (pgList.size() == 0) {
            logger.info("조회된 데이터가 없습니다.");
            return "noData";
        }
        Gson g = new Gson();
        String temp = g.toJson(pgList);
        return temp;
    }

    //  프로그램 상세 조회하기
    @GetMapping("pgDetail")
    public String pgDetail(@RequestParam Map<String, Object> pmap) {
        logger.info("ProgramController2-pgDetail");
        logger.info("pmap: ", pmap);
        List<Map<String, Object>> pgList = null;
        pgList = programService.pgList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(pgList);
        System.out.println(temp);
        return temp;
    }
}