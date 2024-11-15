package com.softtech.demo.modal.user_modal.Commom;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonRespModal {
    private String statusCode;
    private String message;
    private List<?> response;
}
