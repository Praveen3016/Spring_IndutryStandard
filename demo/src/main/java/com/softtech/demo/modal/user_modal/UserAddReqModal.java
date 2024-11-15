package com.softtech.demo.modal.user_modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddReqModal {
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String bankCode;
}
