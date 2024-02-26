package com.sg.silvergarden.dao.empcreate;

import com.sg.silvergarden.vo.empcreate.EmpVO;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class SignupDaoImpl implements SignupDao{


    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null;

    @Override
    public EmpVO save(EmpVO empVO) {

        log.info("save호출");
        log.info(empVO);
        sqlSessionTemplate.insert("empcreateMapper.save", empVO);
        return empVO;


    }

    @Override
    public List<Map<String, Object>> deptname(Map<String, Object> pmap) {
        log.info("deptname-dao호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("empcreateMapper.deptname");
        return list;
    }



}
