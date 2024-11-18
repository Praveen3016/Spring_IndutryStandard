package com.softtech.demo.services;

import com.softtech.demo.entity.user_mst;
import com.softtech.demo.modal.user_modal.Commom.CommonRespModal;
import com.softtech.demo.modal.user_modal.Commom.common_request.CommonReqModal;
import com.softtech.demo.modal.user_modal.Commom.user_modal.UserRespModal;
import com.softtech.demo.modal.user_modal.UserAddReqModal;
import com.softtech.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Service
public class UserServices {

    private final UserRepo userRepository;



    private static final Logger logger = Logger.getLogger(UserServices.class.getName());

    public UserServices(UserRepo userRepository) {
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

    public CommonRespModal updateUser(UserAddReqModal reqModal) {

        String statusCode = "99";
        String message = "User update failed. Please try again";

        try {
            // Validate username
            if (reqModal.getUserName() == null || reqModal.getUserName().isEmpty()) {

                message = "Username cannot be empty.";
            }

            // Fetch user
            user_mst user = userRepository.findByName(reqModal.getUserName());
            if (user == null) {
                message = "User not found." ;
            }

            // Update fields if not null or empty
            user.setName(reqModal.getUserName());
            user.setPhone((reqModal.getPhone() == null || reqModal.getPhone().isEmpty()) ? user.getPhone() : reqModal.getPhone());
            user.setBankCode((reqModal.getBankCode() == null || reqModal.getBankCode().isEmpty()) ? user.getBankCode() : reqModal.getBankCode());
            user.setEmail((reqModal.getEmail() == null || reqModal.getEmail().isEmpty()) ? user.getEmail() : reqModal.getEmail());
            user.setPassword((reqModal.getPassword() == null || reqModal.getPassword().isEmpty()) ? user.getPassword() : reqModal.getPassword());

            // Save user
            userRepository.save(user);
            statusCode = "200";
            message = "User updated successfully";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user: " + e.getMessage(), e);
        }

        // Return response
        CommonRespModal commonRespModal = new CommonRespModal();
        commonRespModal.setStatusCode(statusCode);
        commonRespModal.setMessage(message);
        commonRespModal.setResponse(null);
        return commonRespModal;
    }


    public CommonRespModal deleteUser(UserAddReqModal reqModal) {

        String statusCode = "99";
        String message = "User delete failed. Please try again";
        CommonRespModal commonRespModal = new CommonRespModal();

        try {
            // Validate username
            if (reqModal.getUserName() == null || reqModal.getUserName().isEmpty()) {

                message = "Username cannot be empty.";
                return commonRespModal ;
            }

            // Fetch user
            log.info("user name from request : {}" , reqModal.getUserName());
             userRepository.deleteByName(reqModal.getUserName());

            statusCode = "200";
            message = "User delete successfully";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user: " + e.getMessage(), e);
        }

        // Return response
        commonRespModal.setStatusCode(statusCode);
        commonRespModal.setMessage(message);
        commonRespModal.setResponse(null);
        return commonRespModal;
    }

}
