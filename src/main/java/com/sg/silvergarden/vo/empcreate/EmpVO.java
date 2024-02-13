package com.sg.silvergarden.vo.empcreate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@NoArgsConstructor
public class EmpVO {

    private String e_no;
    private String e_birth;
    private String e_name;
    private String e_phone;
    private String dept_name;
    private String e_email;
    private String e_password;
    private Role e_auth;

    @Builder
    public EmpVO(String e_no, String e_birth, String e_name, String e_phone, String dept_name, String e_email, String e_password, Role e_auth) {
        super();
        this.e_no = e_no;
        this.e_birth = e_birth;
        this.e_name = e_name;
        this.e_phone = e_phone;
        this.dept_name = dept_name;
        this.e_email = e_email;
        this.e_password = e_password;
        this.e_auth = e_auth;

    }


}
