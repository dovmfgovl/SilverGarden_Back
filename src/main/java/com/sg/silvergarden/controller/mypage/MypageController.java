package com.sg.silvergarden.controller.mypage;


import com.google.gson.Gson;
import com.sg.silvergarden.service.mypage.MypageService;
import com.sg.silvergarden.vo.empcreate.EmpVO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/my/*")
public class MypageController {
    @Autowired
    MypageService mypageService=null;
    @GetMapping("mypage")
    public String callMypage(@RequestParam Map<String, Object> mMap) {
        List<Map<String, Object>> mList = null;
        mList = mypageService.callMypage(mMap);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        return temp;
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, Object> pMap){
        int result = mypageService.changePassword(pMap);
        return ResponseEntity.ok(String.valueOf(result));
    }
}


