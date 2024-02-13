package com.sg.silvergarden.service.empcreate;

import com.sg.silvergarden.vo.empcreate.EmpVO;
import com.sg.silvergarden.vo.empcreate.SignupRequest;

import java.util.List;
import java.util.Map;

public interface SignupService {

    EmpVO signup(SignupRequest signupRequest);

    List<Map<String, Object>> deptName(Map<String, Object> pmap);
}
