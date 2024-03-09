package com.sg.silvergarden;

import com.sg.silvergarden.controller.notice.NoticeController;
import com.sg.silvergarden.dao.notice.NoticeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoticeTest {
    @Autowired
    NoticeDao noticeDao;
    @Test
    public void noticeCount() throws Exception{
        Map<String, Object> tmap = new HashMap<>();
        assertEquals(noticeDao.noticeTotalCount(tmap).get("total_count").toString(), String.valueOf(21));
    }
}
