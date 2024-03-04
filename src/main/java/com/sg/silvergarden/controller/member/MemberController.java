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
        List<Map<String, Object>> mList = null;
        mList = memberService.memberList(mMap);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        return temp;
    }
    @GetMapping("memberDelete")
    public String memberDelete(@RequestParam Map<String, Object> mMap){
        int result = 0;
        result = memberService.memberDelete(mMap);
        return String.valueOf(result);
    }

    @GetMapping("memberUpdate")
    public String memberUpdate(@RequestParam Map<String, Object> mMap){
        int result = 0;
        result = memberService.memberUpdate(mMap);
        return String.valueOf(result);
    }
    @PostMapping("memberInsert")
    public String memberInsert(@RequestBody Map<String, Object> mMap){
        int result = 0;
        result = memberService.memberInsert(mMap);
        return String.valueOf(result);
    }
    @GetMapping("counselList")
    public String counselList(@RequestParam Map<String, Object> cMap) {
        List<Map<String, Object>> cList = null;
        cList = memberService.counseList(cMap);
        Gson g = new Gson();
        String temp = g.toJson(cList);
        return temp;
    }
    @GetMapping("counselDelete")
    public String counselDelete(@RequestParam Map<String, Object> cMap){
        int result = 0;
        result = memberService.counselDelete(cMap);
        return String.valueOf(result);
    }

    @GetMapping("counselUpdate")
    public String counselUpdate(@RequestParam Map<String, Object> cMap){
        int result = 0;
        result = memberService.counselUpdate(cMap);
        return String.valueOf(result);
    }
    @PostMapping("counselInsert")
    public String counselInsert(@RequestBody Map<String, Object> cMap){
        log.info(cMap.toString());
        int result = 0;
        result = memberService.counselInsert(cMap);
        return String.valueOf(result);
    }
    @GetMapping("shuttleList")
    public String shuttleList(@RequestParam Map<String,Object> sMap){
        List<Map<String, Object>> sList = null;
        sList=memberService.shuttleList(sMap);
        Gson g = new Gson();
        String temp = g.toJson(sList);
        return temp;
    }
    @GetMapping("shuttleDelete")
    public String shuttleDelete(@RequestParam Map<String, Object> sMap){
        int result = 0;
        result = memberService.shuttleDelete(sMap);
        return String.valueOf(result);
    }

    @GetMapping("shuttleUpdate")
    public String shuttleUpdate(@RequestParam Map<String, Object> sMap){
        int result = 0;
        result = memberService.shuttleUpdate(sMap);
        return String.valueOf(result);
    }
    @PostMapping("shuttleInsert")
    public String shuttleInsert(@RequestBody Map<String, Object> sMap){
        int result = 0;
        result = memberService.shuttleInsert(sMap);
        return String.valueOf(result);
    }

    @GetMapping("shuttleCalList")
    public List<Map<String, Object>> shuttleCalList(@RequestParam Map<String, Object> scMap) {
        List<Map<String, Object>> result = memberService.shuttleCalList(scMap);
        return result;
    }

    @PostMapping("shuttleCalAdd")
    public String ShuttleCalAdd(@RequestBody Map<String, Object> scMap) {
        int result = 0;
        result = memberService.ShuttleCalAdd(scMap);
        return String.valueOf(result);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("shuttleCalUpdate")
    public void shuttleCalUpdate(@RequestBody Map<String, Object> scMap) {
        memberService.shuttleCalUpdate(scMap);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("shuttleCalDelete")
    public void shuttleCalDelete(@RequestParam Map<String, Object> scMap) {
        memberService.shuttleCalDelete(scMap);
    }
}
