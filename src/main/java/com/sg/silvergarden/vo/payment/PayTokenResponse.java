package com.sg.silvergarden.vo.payment;

import lombok.Data;

@Data
public class PayTokenResponse {
    private int code;
    private String message;
    private PayTokenVO response;
}
