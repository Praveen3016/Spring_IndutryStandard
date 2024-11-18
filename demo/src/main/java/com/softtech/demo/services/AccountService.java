package com.softtech.demo.services;

import com.softtech.demo.entity.account_mst;
import com.softtech.demo.entity.user_mst;
import com.softtech.demo.modal.user_modal.Commom.CommonRespModal;
import com.softtech.demo.modal.user_modal.Commom.common_request.AccountReqModel;
import com.softtech.demo.modal.user_modal.Commom.common_request.GetAllAccOfaUser;
import com.softtech.demo.modal.user_modal.Commom.common_request.GetSingleAccModel;
import com.softtech.demo.repository.AccountRepo;
import com.softtech.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    public CommonRespModal addAccount(AccountReqModel request) {
        CommonRespModal commonRespModal = new CommonRespModal();
        String statusCode = "99";
        String message = "Account creation failed. Please try again.";

        // Validate request
        if (request.getUserCd() == null) {
            commonRespModal.setStatusCode(statusCode);
            commonRespModal.setMessage("UserCd cannot be null.");
            return commonRespModal;
        }

        // Fetch the user
        user_mst user = userRepo.findByUserCd(request.getUserCd());
        if (user == null) {
            commonRespModal.setStatusCode(statusCode);
            commonRespModal.setMessage("User not found for UserCd: " + request.getUserCd());
            return commonRespModal;
        }

        try {
            // Create a new account
            account_mst newAccount = new account_mst();
            newAccount.setUser(user);
            newAccount.setAccountType(request.getAccountType());
            newAccount.setLoanAmount(request.getLoanAmount());
            newAccount.setTotalPayedAm(request.getTotalPayedAm());
            newAccount.setTotalDueAm(request.getTotalDueAm());

            // Save the account
            accountRepo.save(newAccount);

            // Set success response
            statusCode = "200";
            message = "Account successfully added for UserCd: " + user.getUserCd();
        } catch (Exception e) {
            log.error("Error occurred while adding account: {}", e.getMessage(), e);
            message = "An unexpected error occurred while adding the account.";
        }

        // Set response
        commonRespModal.setStatusCode(statusCode);
        commonRespModal.setMessage(message);
        commonRespModal.setResponse(null);
        return commonRespModal;
    }

    public CommonRespModal getSingleAccount(GetSingleAccModel request) {
        CommonRespModal commonRespModal = new CommonRespModal();
        String statusCode = "99";
        String message = "Failed to retrieve account details. Please try again.";

        // Validate request
        if (request.getTarn_cd() == null) { // Fixed typo: Tarn_cd -> Tran_cd
            commonRespModal.setStatusCode(statusCode);
            commonRespModal.setMessage("Tran_cd cannot be null.");
            return commonRespModal;
        }

        try {
            // Fetch account by ID
            Optional<account_mst> account = accountRepo.findById(request.getTarn_cd());

            if (account.isPresent()) {
                // Success response
                statusCode = "200";
                message = "Account retrieved successfully.";
                commonRespModal.setResponse(Collections.singletonList(account.get())); // Returning the actual account object
            } else {
                // Account not found
                message = "No account found for Tran_cd: " + request.getTarn_cd();
            }
        } catch (Exception e) {
            // Log the exception and set error message
            log.error("Error occurred while retrieving account: {}", e.getMessage(), e);
            message = "An unexpected error occurred while retrieving the account.";
        }

        // Set response metadata
        commonRespModal.setStatusCode(statusCode);
        commonRespModal.setMessage(message);
        return commonRespModal;
    }

    public CommonRespModal GetAllAccOfaUser(GetAllAccOfaUser request) {

    }
}

