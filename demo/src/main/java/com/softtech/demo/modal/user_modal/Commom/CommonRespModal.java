package com.softtech.demo.modal.user_modal.Commom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class CommonRespModal {
    private String statusCode;
    private String message;
    private List<?> response;
}
