package com.sg.silvergarden.controller.payment;

import com.google.gson.Gson;
import com.sg.silvergarden.service.payment.PaymentService;
import com.sg.silvergarden.vo.payment.PayTokenResponse;
import com.sg.silvergarden.vo.payment.PaymentResponse;
import com.sg.silvergarden.vo.payment.RefundResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${imp_key}")
    private String imp_key;

    @Value("${imp_secret}")
    private String imp_secret;

    @Autowired
    PaymentService paymentService = null;

    @PostMapping("/redirect")
    public void paySuccess(@RequestBody PaymentResponse paymentResponse) {
        log.info("Success@@");
        log.info(paymentResponse);
        paymentService.payUpdate(paymentResponse);
    }

    @GetMapping("paylist")
    public String payList(@RequestParam Map<String, Object> pmap){
        log.info("payList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = paymentService.payList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @GetMapping("payclientlist")
    public String payClientList(@RequestParam Map<String, Object> pmap){
        log.info("payClientList-controller 호출");
        log.info(pmap);
        List<Map<String, Object>> list = null;
        list = paymentService.payClientList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @PostMapping("/refund")
    public void payRefund(@RequestParam Map<String, Object> pmap) {

        String merchant_uid = (String) pmap.get("merchant_uid");

        //access token 발급
        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.add("Content-type", "application/json");
        String jsonBody = "{\"imp_key\": \"" + imp_key + "\", \"imp_secret\": \"" + imp_secret + "\"}";
        HttpEntity<String> tokenRequest = new HttpEntity<>(jsonBody, tokenHeader);
        log.info(tokenRequest);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://api.iamport.kr/users/getToken", HttpMethod.POST, tokenRequest, String.class);
        log.info(response);
        String responsetoken = response.getBody();
        log.info(responsetoken);
        Gson g = new Gson();
        PayTokenResponse res = g.fromJson(responsetoken, PayTokenResponse.class);
        String accessToken = res.getResponse().getAccess_token();
        log.info(accessToken);

        //refund
        log.info("@@@@refund@@@@");
        HttpHeaders refundHeader = new HttpHeaders();
        refundHeader.add("Content-type", "application/json");
        refundHeader.add("Authorization", accessToken);
        log.info(refundHeader);
        String refundBody = "{\"merchant_uid\": \""+ merchant_uid +"\"}";
        log.info(refundBody);
        HttpEntity<String> refundRequest = new HttpEntity<>(refundBody, refundHeader);
        RestTemplate rt2 = new RestTemplate();
        ResponseEntity<String> refundresponse = rt2.exchange("https://api.iamport.kr/payments/cancel", HttpMethod.POST, refundRequest, String.class);
        log.info(refundresponse);

        String responsebody = response.getBody();
        log.info(responsebody);
        Gson g2 = new Gson();
        RefundResponse refundResponse = g.fromJson(responsebody, RefundResponse.class);
        int code = refundResponse.getCode();
        log.info(code);
        if(code == 0){
            paymentService.payRefund(pmap);
        }
    }



}
