package com.sg.silvergarden.controller.member;

import com.google.gson.Gson;
import com.sg.silvergarden.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/member/*")
public class MemberController {
    @Autowired
    MemberService memberService=null;
    @GetMapping("memberList")
    public String memberList(@RequestParam Map<String, Object> mMap) {
        log.info("memberInfo");
        log.info(mMap.toString());
        List<Map<String, Object>> mList = null;
        mList = memberService.memberList(mMap);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        return temp;
    }
    @GetMapping("memberDelete")
    public String memberDelete(@RequestParam Map<String, Object> mMap){
        log.info(mMap.toString());
        int result = -1;
        result = memberService.memberDelete(mMap);
        return "ok";
    }

    @GetMapping("memberUpdate")
    public String memberUpdate(@RequestParam Map<String, Object> mMap){
        log.info(mMap.toString());
        int result = -1;
        result = memberService.memberUpdate(mMap);
        return "ok";
    }
    @PostMapping("memberInsert")
    public String memberInsert(@RequestParam Map<String, Object> mMap){
        log.info(mMap.toString());
//        List<Map<String, Object>> list = new ArrayList<>();
//        mMap.put("list", list);//맵에 파일리스트를 추가해줌
//        log.info(mMap.toString());
        int result = -1;
        result = memberService.memberInsert(mMap);
        return result == 1?"ok":"error";
    }
}
