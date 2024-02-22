package com.sg.silvergarden.dao.programcal;
import com.sg.silvergarden.vo.programcal.ProgramSchedule;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProgramScheduleDao {

    Logger logger = LoggerFactory.getLogger(ProgramScheduleDao.class);

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public String test() {
        logger.info("ProgramScheduleDao - test");
        return null;
    }

    public List<Map<String, Object>> pgCalendarList(Map<String, Object> pmap) {
        logger.info("ProgramScheduleDao - pgCalendarList");
        return sqlSessionTemplate.selectList("pgCalendarList", pmap);
    }

    public int pgCalendarAdd(Map<String, Object> pamp) {
        logger.info("ProgramScheduleDao - pgCalendarAdd");
        return sqlSessionTemplate.insert("pgCalendarAdd", pamp);
    }

    public int pgCalendarDelete(Map<String, Object> psNo) {
        logger.info("ProgramScheduleDao - pgCalendarDelete");
        return sqlSessionTemplate.delete("pgCalendarDelete", psNo);
    }

    public int pgCalendarUpdate(Map<String, Object> pmap) {
        logger.info("ProgramScheduleDao - pgCalendarUpdate");
        logger.info("pmap:"+pmap);

        return sqlSessionTemplate.update("pgCalendarUpdate", pmap);
    }
}