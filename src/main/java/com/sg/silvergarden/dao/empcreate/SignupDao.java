package com.sg.silvergarden.dao.empcreate;

import com.sg.silvergarden.vo.empcreate.EmpVO;

import java.util.List;
import java.util.Map;

public interface SignupDao {

    EmpVO save(EmpVO empVO);

    List<Map<String, Object>> deptname(Map<String, Object> pmap);
}
