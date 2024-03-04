package com.sg.silvergarden.dao.payment;

import com.sg.silvergarden.vo.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null;

    public void payInsert(Map<String, Object> pmap) {
        log.info("payInsert-dao호출");
        log.info(pmap);
        sqlSessionTemplate.insert("paymentMapper.payInsert",pmap);
    }

    public void payUpdate(PaymentResponse paymentResponse) {
        log.info("payUpdate-dao호출");
        log.info(paymentResponse);
        sqlSessionTemplate.update("paymentMapper.payUpdate",paymentResponse);
    }

    public List<Map<String, Object>> payList(Map<String, Object> pmap) {
        log.info("deptList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("paymentMapper.payList", pmap);
        log.info(list);
        return list;
    }

    public void payRefund(Map<String, Object> pmap) {
        log.info("payRefund-dao호출");
        log.info(pmap);
        sqlSessionTemplate.update("paymentMapper.payRefund",pmap);
    }

    public List<Map<String, Object>> payClientList(Map<String, Object> pmap) {
        log.info("payClientList-dao 호출");
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("paymentMapper.payClientList", pmap);
        log.info(list);
        return list;
    }
}
