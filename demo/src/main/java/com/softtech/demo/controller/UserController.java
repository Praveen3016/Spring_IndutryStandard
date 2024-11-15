package com.softtech.demo.controller;

import com.softtech.demo.modal.user_modal.Commom.CommonRespModal;
import com.softtech.demo.modal.user_modal.Commom.common_request.CommonReqModal;
import com.softtech.demo.modal.user_modal.UserAddReqModal;
import com.softtech.demo.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping(value = "user_add_")
    public CommonRespModal addUser(@RequestBody UserAddReqModal request) {
        return userServices.addUser(request);
    }


    @PostMapping(value = "get_user_list")
    public CommonRespModal getUserList(@RequestBody CommonReqModal request) {
        return userServices.getUserList(request);
    }
}
