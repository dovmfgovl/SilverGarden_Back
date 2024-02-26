package com.sg.silvergarden.controller.shuttle;

import com.google.gson.Gson;
import com.sg.silvergarden.service.shuttle.ShuttleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shuttle")
public class ShuttleController {
    @Autowired
    ShuttleService shuttleService=null;
    @GetMapping("shuttleList")
    public String shuttleList(@RequestParam Map<String,Object> sMap){
        log.info("shuttleList");
        log.info(sMap.toString());
        List<Map<String, Object>> sList = null;
        sList=shuttleService.shuttleList(sMap);
        Gson g = new Gson();
        String temp = g.toJson(sList);
        return temp;
    }
    @GetMapping("shuttleDelete")
    public String shuttleDelete(@RequestParam Map<String, Object> sMap){
        log.info(sMap.toString());
        int result = 0;
        result = shuttleService.shuttleDelete(sMap);
        log.info(String.valueOf(result));
        return String.valueOf(result);
    }

    @GetMapping("shuttleUpdate")
    public String shuttleUpdate(@RequestParam Map<String, Object> sMap){
        log.info(sMap.toString());
        int result = 0;
        result = shuttleService.shuttleUpdate(sMap);
        log.info(String.valueOf(result));
        return String.valueOf(result);
    }
    @PostMapping("shuttleInsert")
    public String shuttleInsert(@RequestBody Map<String, Object> sMap){
        log.info(sMap.toString());
        int result = 0;
        result = shuttleService.shuttleInsert(sMap);
        log.info(String.valueOf(result));
        return String.valueOf(result);
    }
}
