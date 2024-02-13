package com.sg.silvergarden.vo.empcreate;

import lombok.Data;

@Data
public class SignupRequest {
    private String e_birth;
    private String e_name;
    private String e_phone;
    private String dept_name;
    private String e_email;
    private String e_password;
    private Role e_auth;

}
