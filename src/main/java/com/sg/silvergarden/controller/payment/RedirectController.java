package com.sg.silvergarden.controller.payment;

import com.sg.silvergarden.service.payment.PaymentService;
import com.sg.silvergarden.vo.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class RedirectController {

    @Autowired
    PaymentService paymentService = null;

    @PostMapping("/redirect")
    public void paySuccess(@RequestBody PaymentResponse paymentResponse) {
        paymentService.payUpdate(paymentResponse);
    }

}
