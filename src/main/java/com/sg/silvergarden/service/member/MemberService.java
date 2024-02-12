package com.sg.silvergarden.service.member;

import com.sg.silvergarden.dao.member.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService{
    @Autowired
    MemberDao memberDao=null;
    public List<Map<String, Object>> memberList(Map<String, Object> mMap) {
        List<Map<String,Object>> mList = null;
        mList=memberDao.memberList(mMap);
        return mList;
    }

    public int memberDelete(Map<String, Object> mMap) {
            int result = -1;
            result = memberDao.memberDelete(mMap);
            return result;

    }
    public int memberUpdate(Map<String, Object> mMap) {
            int result = -1;
            result = memberDao.memberUpdate(mMap);
            return result;

    }
    public int memberInsert(Map<String, Object> mMap) {
        int result = -1;
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
}
