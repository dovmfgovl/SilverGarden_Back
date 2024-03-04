package com.sg.silvergarden.service.payment;

import com.google.gson.Gson;
import com.sg.silvergarden.vo.payment.PayTokenResponse;
import com.sg.silvergarden.vo.payment.UrlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;

@Log4j2
@Service
public class PayUrlServiceImpl implements PayUrlService{

    @Value("${impCode}")
    private String code;

    @Value("${imp_key}")
    private String imp_key;

    @Value("${imp_secret}")
    private String imp_secret;

    @Override
    public String generateUrl(Map<String, Object> pmap) {

        String amountStr = (String) pmap.get("amount");
        int amount = Integer.parseInt(amountStr);
        String name = (String) pmap.get("name");
        String orderno = (String) pmap.get("orderno");
        String phone = (String) pmap.get("phone");
        // 현재 시간 기준으로 1일을 더한 시간을 얻음
        Instant tomorrow = Instant.now().plusSeconds(86400); // 1일 = 86400초
        // 타임스탬프 생성 (초 단위)
        long timestemp = tomorrow.getEpochSecond();

        //결제링크 생성
        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.add("Content-type", "application/json;charset=UTF-8");
        String jsonBody = "{\"payment_info\":\"{\\\"title\\\":\\\"결제하기\\\",\\\"user_code\\\":\\\""+code+"\\\",\\\"amount\\\":"+amount+",\\\"merchant_uid\\\":\\\""+orderno+"\\\",\\\"name\\\":\\\"실버가든 이용권\\\",\\\"tax_free\\\":\\\"면세공급가액\\\",\\\"currency\\\":\\\"KRW\\\",\\\"language\\\":\\\"ko\\\",\\\"buyer_name\\\":\\\""+name+"\\\",\\\"buyer_tel\\\":\\\""+phone+"\\\",\\\"buyer_addr\\\":\\\"\\\",\\\"buyer_email\\\":\\\"\\\",\\\"buyer_postcode\\\":\\\"\\\",\\\"custom_data\\\":\\\"json_object\\\",\\\"pay_methods\\\":[{\\\"pg\\\":\\\"html5_inicis.INIpayTest\\\",\\\"pay_method\\\":\\\"card\\\",\\\"label\\\":\\\"신용/체크카드\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"phone\\\",\\\"label\\\":\\\"핸드폰 소액결제\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"trans\\\",\\\"label\\\":\\\"계좌이체\\\"},{\\\"pg\\\":\\\"INIpayTest\\\",\\\"pay_method\\\":\\\"vbank\\\",\\\"label\\\":\\\"가상계좌\\\"}]}\",\"expired_at\":"+timestemp+"}";
        HttpEntity<String> tokenRequest = new HttpEntity<>(jsonBody, tokenHeader);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://api.iamport.co/api/supplements/v1/link/payment", HttpMethod.POST, tokenRequest, String.class);
        String responseBody = response.getBody();
        Gson g = new Gson();
        UrlResponse res = g.fromJson(responseBody, UrlResponse.class);
        String url = res.getShortenedUrl();

        return url;
    }

    @Override
    public String getToken() {
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
        return accessToken;
    }
}
