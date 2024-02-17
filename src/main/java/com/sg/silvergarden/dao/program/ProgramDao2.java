package com.sg.silvergarden.dao.program;

import com.sg.silvergarden.vo.programcal.ProgramSchedule;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProgramDao2 {
    Logger logger = LoggerFactory.getLogger(ProgramDao2.class);
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> pgList(Map<String, Object> pmap) {
        logger.info("ProgramDao2-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = sqlSessionTemplate.selectList("pgList", pmap);
        return pgList;
    }

    public int pgInsert(Map<String, Object> pmap) {
        logger.info("ProgramDao2-pgInsert");
        logger.info(pmap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("pgInsert",pmap);
        return result;
    }

    public int pgDelete(Map<String, Object> pmap) {
        logger.info("ProgramDao2-pgDelete");
        logger.info(pmap.toString()); // 파라미터 출력 추가
        int result = 0;
        result = sqlSessionTemplate.delete("pgDelete",pmap);
        return result;
    }

    public List<ProgramSchedule> scheduleList() {
        logger.info("ProgramDao2-scheduleList");
        List<ProgramSchedule> calList = null;
        calList = sqlSessionTemplate.selectList("scheduleList");
        return calList;
    }

    public int pgUpdate(Map<String, Object> pmap) {
        logger.info("ProgramDao2-pgUpdate");
        int result = sqlSessionTemplate.update("pgUpdate", pmap);
        return result;
    }

    public int pgCalInsert(Map<String, Object> pmap) {
        logger.info("ProgramDao2-pgCalInsert");
        logger.info(pmap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("pgCalInsert",pmap);
        return result;
    }
}
