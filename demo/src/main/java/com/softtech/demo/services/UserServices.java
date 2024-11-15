package com.softtech.demo.services;

import com.softtech.demo.entity.user_mst;
import com.softtech.demo.modal.user_modal.Commom.CommonRespModal;
import com.softtech.demo.modal.user_modal.Commom.common_request.CommonReqModal;
import com.softtech.demo.modal.user_modal.Commom.user_modal.UserRespModal;
import com.softtech.demo.modal.user_modal.UserAddReqModal;
import com.softtech.demo.repository.userRepo;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserServices {

    private final userRepo userRepository;

    private static final Logger logger = Logger.getLogger(UserServices.class.getName());

    public UserServices(userRepo userRepository) {
        this.userRepository = userRepository;
    }
    public CommonRespModal addUser(UserAddReqModal reqModal) {

        String statusCode = "99";
        String message = "user add failed.please try again";
        try {

            String userName = reqModal.getUserName();
            String password = reqModal.getPassword();
            String email = reqModal.getEmail();
            String phone = reqModal.getPhone();
            String bankCode = reqModal.getBankCode();

            user_mst userLog = new user_mst();
            userLog.setPhone(phone);
            userLog.setBankCode(bankCode);
            userLog.setEmail(email);
            userLog.setPassword(password);
            userLog.setName(userName);
            userRepository.save(userLog);
            statusCode = "200";
            message = "user added successfully";

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        CommonRespModal commonRespModal =new CommonRespModal();
        commonRespModal.setMessage(statusCode);
        commonRespModal.setStatusCode(message);
        commonRespModal.setResponse(null);
        return commonRespModal;
    }

    public CommonRespModal getUserList(CommonReqModal reqModal) {

        String statusCode = "99";
        String message = "user add failed.please try again";

        List<UserRespModal> userList = null;
        try {
            String bankCode = reqModal.getBankCode();

           List<user_mst> log = userRepository.findByBankCode(bankCode);
           if(!log.isEmpty()) {
               userList = new ArrayList();
               for (user_mst userLog : log) {
                   UserRespModal userRespModal = new UserRespModal();
                   userRespModal.setBankCode(userLog.getBankCode());
                   userRespModal.setEmail(userLog.getEmail());
                   userRespModal.setPhone(userLog.getPhone());
                   userRespModal.setUserName(userLog.getName());
                   userList.add(userRespModal);
               }
               statusCode  = "200";
               message = "user list successfully";
           }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        CommonRespModal commonRespModal =new CommonRespModal();
        commonRespModal.setMessage(statusCode);
        commonRespModal.setStatusCode(message);
        commonRespModal.setResponse(userList);
        return commonRespModal;
    }
}
