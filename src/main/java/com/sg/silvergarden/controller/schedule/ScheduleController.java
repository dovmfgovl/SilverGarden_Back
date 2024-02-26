package com.sg.silvergarden.controller.schedule;

import com.google.gson.Gson;
import com.sg.silvergarden.service.dept.DeptService;
import com.sg.silvergarden.service.schedule.ScheduleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "http://localhost:3000/")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService = null;

    @GetMapping("schedulelist")
    public String scheduleList(@RequestParam Map<String, Object> pmap){
        log.info("scheduleList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = scheduleService.scheduleList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @PostMapping("scheduleinsert")
    public void scheduleInsert(@RequestBody Map<String, Object> pmap){
        log.info("scheduleInsert-controller 호출");
        log.info(pmap);
        scheduleService.scheduleInsert(pmap);
    }

    @PutMapping("scheduleupdate")
    public void scheduleUpdate(@RequestBody Map<String, Object> pmap){
        log.info("scheduleUpdate-controller 호출");
        log.info(pmap);
        scheduleService.scheduleUpdate(pmap);
    }

    @DeleteMapping("scheduledelete")
    public void scheduleDelete(@RequestParam Map<String, Object> pmap){
        log.info("scheduleDelete-controller 호출");
        log.info(pmap);
        scheduleService.scheduleDelete(pmap);
    }


}
