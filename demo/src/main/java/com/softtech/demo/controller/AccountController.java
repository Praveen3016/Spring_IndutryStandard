package com.softtech.demo.controller;


import com.softtech.demo.modal.user_modal.Commom.CommonRespModal;
import com.softtech.demo.modal.user_modal.Commom.common_request.AccountReqModel;
import com.softtech.demo.modal.user_modal.Commom.common_request.GetAllAccOfaUser;
import com.softtech.demo.modal.user_modal.Commom.common_request.GetSingleAccModel;
import com.softtech.demo.modal.user_modal.UserAddReqModal;
import com.softtech.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService ;

    @PostMapping(value = "add_account")
    public CommonRespModal addAccount(@RequestBody AccountReqModel request) {
        return accountService.addAccount(request);
    }

    @PostMapping(value = "get_single_account")
    public CommonRespModal getSingleAccount(@RequestBody GetSingleAccModel request) {
        return accountService.getSingleAccount(request);
    }

    @PostMapping(value = "get_accounts_of_user")
    public CommonRespModal GetAllAccOfaUser(@RequestBody GetAllAccOfaUser request) {
        return accountService.GetAllAccOfaUser(request);
    }
}
