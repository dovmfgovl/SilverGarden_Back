package com.sg.silvergarden.controller.payment;

import com.google.gson.Gson;
import com.sg.silvergarden.service.payment.PaymentService;
import com.sg.silvergarden.vo.payment.UrlResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@RestController
@RequestMapping("/sms")
public class SmsUtil {
    private String apiKey = "NCSOU1NXOTTZYPSW";

    private String apiSecretKey = "XW7C82RR45SLST9E1OURRA8VR8LAEBWM";

    private DefaultMessageService messageService;

    @Autowired
    PaymentService paymentService = null;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    // 단일 메시지 발송 예제
    @PostMapping("request")
    public SingleMessageSentResponse sendOne(@RequestParam Map<String, Object> pmap) {
log.info(pmap);
        String code = "imp45346535";
        String amountStr = (String) pmap.get("amount");
        int amount = Integer.parseInt(amountStr);
        String name = (String) pmap.get("name");
        String orderno = (String) pmap.get("orderno");
        String phone = (String) pmap.get("phone");

        // 현재 시간 기준으로 1일을 더한 시간을 얻음
        Instant tomorrow = Instant.now().plusSeconds(86400); // 1일 = 86400초
        // 타임스탬프 생성 (초 단위)
        long timestemp = tomorrow.getEpochSecond();
        log.info(timestemp);

        //payment db에 값 저장
        paymentService.payInsert(pmap);
        log.info(pmap);

        //sms요청
        log.info("sms요청");
        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.add("Content-type", "application/json;charset=UTF-8");
        log.info(tokenHeader);//[Content-type:"application/json"]

        String jsonBody = "{\"payment_info\":\"{\\\"title\\\":\\\"결제하기\\\",\\\"user_code\\\":\\\""+code+"\\\",\\\"amount\\\":"+amount+",\\\"merchant_uid\\\":\\\""+orderno+"\\\",\\\"name\\\":\\\"실버가든 이용권\\\",\\\"tax_free\\\":\\\"면세공급가액\\\",\\\"currency\\\":\\\"KRW\\\",\\\"language\\\":\\\"ko\\\",\\\"buyer_name\\\":\\\""+name+"\\\",\\\"buyer_tel\\\":\\\""+phone+"\\\",\\\"buyer_addr\\\":\\\"\\\",\\\"buyer_email\\\":\\\"\\\",\\\"buyer_postcode\\\":\\\"\\\",\\\"custom_data\\\":\\\"json_object\\\",\\\"pay_methods\\\":[{\\\"pg\\\":\\\"html5_inicis.INIpayTest\\\",\\\"pay_method\\\":\\\"card\\\",\\\"label\\\":\\\"신용/체크카드\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"phone\\\",\\\"label\\\":\\\"핸드폰 소액결제\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"trans\\\",\\\"label\\\":\\\"계좌이체\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"vbank\\\",\\\"label\\\":\\\"가상계좌\\\"}]}\",\"expired_at\":"+timestemp+"}";
        log.info(jsonBody);

        HttpEntity<String> tokenRequest = new HttpEntity<>(jsonBody, tokenHeader);
        log.info(tokenRequest);//
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://api.iamport.co/api/supplements/v1/link/payment", HttpMethod.POST, tokenRequest, String.class);
        log.info(response);//<200 OK OK,{"code":0,"message":null,"response":{"access_token":"081a78da5111c2d2348c083a09242e8d892ee47d","now":1707118196,"expired_at":1707119996}},[Date:"Mon, 05 Feb 2024 07:29:56 GMT", Content-Type:"application/json; charset=UTF-8", Content-Length:"137", Connection:"keep-alive", Server:"Apache", X-Content-Type-Options:"nosniff", X-XSS-Protection:"1; mode=block", X-Frame-Options:"SAMEORIGIN"]>
        String responseBody = response.getBody();
        log.info(responseBody);//{"code":0,"message":null,"response":{"access_token":"081a78da5111c2d2348c083a09242e8d892ee47d","now":1707118196,"expired_at":1707119996}}
        Gson g = new Gson();
        UrlResponse res = g.fromJson(responseBody, UrlResponse.class);
        String url = res.getShortenedUrl();
        log.info(url);

        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setTo(phone);
        message.setFrom("01072379676");
        message.setText("[실버가든]\n안녕하세요." + name + "고객님,\n실버가든 이용을 위하여 하단 링크를 통해 결제 진행 부탁드립니다. \n감사합니다.\n" + url);

        SingleMessageSentResponse msg = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return msg;
    }
}
