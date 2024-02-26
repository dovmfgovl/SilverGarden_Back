package com.sg.silvergarden.service.shuttle;

import com.sg.silvergarden.dao.shuttle.ShuttleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class ShuttleService {
   @Autowired
   ShuttleDao shuttleDao = null;
    public List<Map<String, Object>> shuttleList(Map<String, Object> sMap) {
        List<Map<String,Object>> sList = null;
        sList=shuttleDao.shuttleList(sMap);
        return sList;
    }

    public int shuttleDelete(Map<String, Object> sMap) {
        int result = 0;
        result = shuttleDao.shuttleDelete(sMap);
        return result;

    }
    public int shuttleUpdate(Map<String, Object> sMap) {
        int result = 0;
        result = shuttleDao.shuttleUpdate(sMap);
        return result;

    }
    public int shuttleInsert(Map<String, Object> sMap) {
        int result = 0;
        result = shuttleDao.shuttleInsert(sMap);
        //  }
        return result;
    }


}
