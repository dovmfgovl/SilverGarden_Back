package com.sg.silvergarden.controller.payment;

import com.google.gson.Gson;
import com.sg.silvergarden.service.payment.PayUrlService;
import com.sg.silvergarden.service.payment.PaymentService;
import com.sg.silvergarden.vo.payment.UrlResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/sms")
public class SmsUtil {

    @Value("${smsapiKey}")
    private String apiKey;

    @Value("${smsSecretkey}")
    private String apiSecretKey;

    private DefaultMessageService messageService;
    private final PayUrlService payUrlService;

    @Autowired
    PaymentService paymentService = null;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    // 단일 메시지 발송 예제
    @PostMapping("request")
    public SingleMessageSentResponse sendOne(@RequestBody Map<String, Object> pmap) {


        String name = (String) pmap.get("name");
        String phone = (String) pmap.get("phone");

        //payment db에 값 저장
        paymentService.payInsert(pmap);

        //결제링크 생성
        String url = payUrlService.generateUrl(pmap);
        log.info(url);

        //sms form(수신번호/발신번호는 무조건 숫자 형태로 입력되어야 함 ex)01012345678)
        Message message = new Message();
        message.setTo(phone);
        message.setFrom("01072379676");
        message.setText("[실버가든]\n안녕하세요." + name + "고객님,\n실버가든 이용을 위하여 하단 링크를 통해 결제 진행 부탁드립니다. \n감사합니다.\n" + url);

        SingleMessageSentResponse msg = this.messageService.sendOne(new SingleMessageSendingRequest(message));

        return msg;
    }
}
