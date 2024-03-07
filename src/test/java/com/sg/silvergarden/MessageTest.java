package com.sg.silvergarden;

import com.sg.silvergarden.dao.message.MessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageTest {
    @Autowired
    MessageDao messageDao;

    @Test
    public void messageRecipientInsertTest() throws Exception{
        List<Map<String, Object>> receiverList = new ArrayList<>();
        Map<String, Object> rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 1);
        receiverList.add(rmap);
        rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 2);
        receiverList.add(rmap);
        rmap = new HashMap<>();
        rmap.put("me_no", 1);
        rmap.put("r_id", 3);
        receiverList.add(rmap);
        assertEquals(messageDao.messageRecipientInsert(receiverList), 3);
    }
    @Test
    public void messsageReceiveList() throws Exception{
        Map<String, Object> rmap = new HashMap<>();
        rmap.put("e_no","202402_00000030");
        rmap.put("gubun", "period");
        rmap.put("start_date", "2024-02-21");
        rmap.put("end_date", "2024-02-22");
        assertEquals(messageDao.messageReceiveList(rmap).size(), 4);
    }
    @Test
    public void messageSendList() throws Exception{
        Map<String, Object> rmap = new HashMap<>();
        assertEquals(messageDao.messageSendList(rmap).size(), 5);
    }
}
