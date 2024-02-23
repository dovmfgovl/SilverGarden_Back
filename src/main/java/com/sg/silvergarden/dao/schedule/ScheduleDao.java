package com.sg.silvergarden.dao.schedule;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ScheduleDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null;
    public List<Map<String, Object>> scheduleList(Map<String, Object> pmap) {
        log.info("scheduleList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("scheduleMapper.scheduleList", pmap);
        log.info(list);

        return list;
    }

    public void scheduleInsert(Map<String, Object> pmap) {
        log.info("scheduleInsert-dao호출");
        sqlSessionTemplate.insert("scheduleMapper.scheduleInsert", pmap);
    }

    public void scheduleUpdate(Map<String, Object> pmap) {
        log.info("scheduleUpdate-dao호출");
        sqlSessionTemplate.update("scheduleMapper.scheduleUpdate", pmap);
    }

    public void scheduleDelete(Map<String, Object> pmap) {
        log.info("scheduleDelete-dao호출");
        sqlSessionTemplate.delete("scheduleMapper.scheduleDelete", pmap);
    }
}
