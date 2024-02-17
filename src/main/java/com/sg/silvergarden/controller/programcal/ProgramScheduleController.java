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
    public List<Map<String, Object>> getProgramSchedules(@RequestParam Map<String, Object> pmap) {
        logger.info("getProgramSchedules");
        List<Map<String, Object>> result = programScheduleService.getProgramSchedule(pmap);
        logger.info(pmap.toString());
        return result;
    }

    @PostMapping("add")
    public String addProgramSchedule(@RequestBody Map<String, Object> pmap) {
        logger.info("addProgramSchedule", pmap);
        int result = 0;
        result = programScheduleService.addProgramSchedule(pmap);
        return String.valueOf(result);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("update")
    public void updateProgramSchedule(@RequestBody Map<String, Object> pmap) {
        logger.info("updateProgramSchedule", pmap);
        programScheduleService.updateProgramSchedule(pmap);
    }

    @DeleteMapping("delete")
    public void deleteProgramSchedule(@RequestBody Map<String, Object> psNo2) {
        logger.info("deleteProgramSchedule", psNo2);
        programScheduleService.deleteProgramSchedule(psNo2);
    }
}