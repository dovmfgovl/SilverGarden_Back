package com.sg.silvergarden.controller.message;

import com.google.gson.Gson;
import com.sg.silvergarden.config.YAMLConfiguration;
import com.sg.silvergarden.service.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/message/*")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    YAMLConfiguration config;

    @GetMapping("messageReceiveList")
    public String messageReceiveList(String e_no){
        log.info("messageReceiveList");
        List<Map<String, Object>> mList = null;
        mList = messageService.messageReceiveList(e_no);
        Gson g = new Gson();
        String temp = g.toJson(mList);
        log.info(temp);
        return temp;
    }

    @PostMapping("messageSend")
    public String messageSend(@RequestParam Map<String, Object> pmap, @RequestParam(name="files", required = false) MultipartFile[] files ){
        log.info(pmap.toString());
        List<Map<String, Object>> list = new ArrayList<>();
        if(files != null){//파일이 있는 경우
            for(MultipartFile file : files){
                Map<String, Object> nmap = new HashMap<>();
                String originalFilename = file.getOriginalFilename();
                String uploadFilename = getCurrentTimeMillisFormat() + "_" + FilenameUtils.getName(originalFilename);
                File upFile = new File(config.getUploadPath(), uploadFilename);//지정된 경로에 파일저장
                try {
                    file.transferTo(upFile);
                    nmap.put("send_id", pmap.get("send_id"));
                    nmap.put("me_filepath", config.getUploadPath());
                    nmap.put("me_fileorigin", originalFilename);
                    nmap.put("me_filename", uploadFilename);
                    list.add(nmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            pmap.put("list", list);//맵에 파일리스트를 추가해줌
        }
        log.info(pmap.toString());
        int result = -1;
        result = messageService.messageSend(pmap);
        return result == 0?"error":"ok";
    }
    private String getCurrentTimeMillisFormat() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date(currentTime));
    }
}
