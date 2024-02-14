package com.sg.silvergarden.dao.program;

import com.sg.silvergarden.vo.program.ProgramVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Repository
public class ProgramDao{
    Logger logger = LoggerFactory.getLogger(ProgramDao.class);
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @GetMapping("test")

    public String test(){
        logger.info("ProgramDao");
        return null;
    }

    public List<Map<String, Object>> pgList(Map<String, Object> pmap) {
        logger.info("ProgramDao-pgList");
        List<Map<String, Object>> pgList = null;
        pgList = sqlSessionTemplate.selectList("pgList", pmap);
        return pgList;
    }

    public int pgInsert(Map<String, Object> pmap) {
        logger.info("ProgramDao-pgInsert");
        logger.info(pmap.toString());
        int result = 0;
        result = sqlSessionTemplate.insert("pgInsert",pmap);
        return result;
    }

    public int pgDelete(Map<String, Object> pmap) {
        logger.info("ProgramDao-pgDelete");
        logger.info(pmap.toString()); // 파라미터 출력 추가
        int result = 0;
        result = sqlSessionTemplate.delete("pgDelete",pmap);
        return result;
    }

    public List<ProgramVO> scheduleList() {
        logger.info("ProgramDao-scheduleList");
        List<ProgramVO> calList = null;
        calList = sqlSessionTemplate.selectList("scheduleList");
        return calList;
    }

    public int pgUpdate(Map<String, Object> pmap) {
        logger.info("ProgramDao-pgUpdate");
        int result = sqlSessionTemplate.update("pgUpdate", pmap);
        return result;
    }
}
