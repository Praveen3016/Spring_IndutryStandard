package com.softtech.demo.modal.user_modal.Commom.common_request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountReqModel {
    private Integer userCd ;
    private String accountType;
    private Integer loanAmount;
    private Integer totalPayedAm;
    private Integer totalDueAm;

}
