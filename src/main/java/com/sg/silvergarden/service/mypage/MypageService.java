package com.sg.silvergarden.service.mypage;

import com.sg.silvergarden.dao.member.MemberDao;
import com.sg.silvergarden.dao.mypage.MypageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MypageService {
    private final MypageDao mypageDao;
    private final PasswordEncoder passwordEncoder;

    public MypageService(PasswordEncoder passwordEncoder, MypageDao mypageDao) {
        this.passwordEncoder = passwordEncoder;
        this.mypageDao = mypageDao;
    }

    public List<Map<String, Object>> callMypage(Map<String, Object> mMap) {
        List<Map<String,Object>> mList = null;
        mList= mypageDao.callMypage(mMap);
        return mList;
    }


  public int changePassword(Map<String, Object> pMap) {
        log.info("changePassword-service");
        String newPassword = (String) pMap.get("E_PASSWORD");
        log.info(newPassword);
        String encryptedPassword = passwordEncoder.encode(newPassword);
        pMap.put("E_PASSWORD",encryptedPassword);
        int result = mypageDao.changePassword(pMap);
        return result;
    }
}
