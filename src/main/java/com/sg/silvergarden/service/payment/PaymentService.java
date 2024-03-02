package com.sg.silvergarden.service.payment;

import com.sg.silvergarden.dao.payment.PaymentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    PaymentDao paymentDao = null;
    public void payInsert(Map<String, Object> pmap) {
        log.info("payInsert-service 호출");
        log.info(pmap);
        paymentDao.payInsert(pmap);
    }

    public void payUpdate(Map<String, Object> pmap) {
        log.info("payUpdate-service 호출");
        log.info(pmap);
        paymentDao.payUpdate(pmap);
    }

    public List<Map<String, Object>> payList(Map<String, Object> pmap) {
        log.info("payList-dao 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = paymentDao.payList(pmap);

        return list;
    }

    public void payRefund(Map<String, Object> pmap) {
        log.info("payRefund-service 호출");
        log.info(pmap);
        paymentDao.payRefund(pmap);
    }

    public List<Map<String, Object>> payClientList(Map<String, Object> pmap) {
        log.info("payClientList-dao 호출");
        List<Map<String, Object>> list = new ArrayList<>();
        list = paymentDao.payClientList(pmap);

        return list;
    }
}
