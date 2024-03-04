package com.sg.silvergarden.vo.payment;

import lombok.Data;

@Data
public class PaymentResponse {
    private String imp_uid;
    private String merchant_uid;
    private String status;
}
