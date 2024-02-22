package com.sg.silvergarden.controller.programcal;

import com.sg.silvergarden.service.programcal.ProgramScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar/*")
public class ProgramScheduleController {
    Logger logger = LoggerFactory.getLogger(ProgramScheduleController.class);
    @Autowired
    ProgramScheduleService programScheduleService;

    @GetMapping("list")
    public List<Map<String, Object>> pgCalendarList(@RequestParam Map<String, Object> pmap) {
        logger.info("pgCalendarList");
        List<Map<String, Object>> result = programScheduleService.pgCalendarList(pmap);
        logger.info(pmap.toString());
        return result;
    }

    @PostMapping("add")
    public String pgCalendarAdd(@RequestBody Map<String, Object> pmap) {
        logger.info("pgCalendarAdd", pmap);
        int result = 0;
        result = programScheduleService.pgCalendarAdd(pmap);
        return String.valueOf(result);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("update")
    public void pgCalendarUpdate(@RequestBody Map<String, Object> pmap) {
        logger.info("pgCalendarUpdate", pmap);
        programScheduleService.pgCalendarUpdate(pmap);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("delete")
    public void pgCalendarDelete(@RequestParam Map<String, Object> psNo2) {
        logger.info("pgCalendarDelete", psNo2);
        programScheduleService.pgCalendarDelete(psNo2);
    }
}