package com.sg.silvergarden.service.member;

import com.sg.silvergarden.dao.member.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberService{
    @Autowired
    MemberDao memberDao=null;
    public List<Map<String, Object>> memberList(Map<String, Object> mMap) {
        List<Map<String,Object>> mList = null;
        mList=memberDao.memberList(mMap);
        return mList;
    }

    public int memberDelete(Map<String, Object> mMap) {
            int result = 0;
            result = memberDao.memberDelete(mMap);
            return result;

    }
    public int memberUpdate(Map<String, Object> mMap) {
            int result = 0;
            result = memberDao.memberUpdate(mMap);
            return result;

    }
    public int memberInsert(Map<String, Object> mMap) {
        int result = 0;
//        if(mMap.containsKey("list")){//파일이 있는 경우 memberInsert를 먼저하고 시퀀스 값을 받아옴
//            List<Map<String, Object>> flist = (List)mMap.get("list");
//            memberDao.memberInsert(mMap);
//            for(Map<String, Object> map : flist){
//                map.put("member_id", mMap.get("member_id"));
//            }
//            result = memberDao.fileUpload(flist);
//        }else{
            result = memberDao.memberInsert(mMap);
      //  }
        return result;
    }

    public List<Map<String, Object>> counseList(Map<String, Object> cMap) {
        List<Map<String,Object>> cList = null;
        cList=memberDao.counselList(cMap);
        return cList;
    }

    public int counselDelete(Map<String, Object> cMap) {
        log.info("counselDelete");
        int result = 0;
        result = memberDao.counselDelete(cMap);
        return result;
    }

    public int counselUpdate(Map<String, Object> cMap) {
        int result = 0;
        result = memberDao.counselUpdate(cMap);
        return result;
    }

    public int counselInsert(Map<String, Object> cMap) {
        int result = 0;
        result = memberDao.counselInsert(cMap);
        return result;
    }

    public List<Map<String, Object>> shuttleList(Map<String, Object> sMap) {
        List<Map<String,Object>> sList = null;
        sList=memberDao.shuttleList(sMap);
        return sList;
    }

    public int shuttleDelete(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleDelete(sMap);
        return result;

    }
    public int shuttleUpdate(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleUpdate(sMap);
        return result;

    }
    public int shuttleInsert(Map<String, Object> sMap) {
        int result = 0;
        result = memberDao.shuttleInsert(sMap);
        //  }
        return result;
    }
}
