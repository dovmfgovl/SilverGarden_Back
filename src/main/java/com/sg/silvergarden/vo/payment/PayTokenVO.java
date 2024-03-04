package com.sg.silvergarden.vo.payment;

import lombok.Data;

@Data
public class PayTokenVO {
    private String access_token;
    private int expires_at;
    private int now;
}
