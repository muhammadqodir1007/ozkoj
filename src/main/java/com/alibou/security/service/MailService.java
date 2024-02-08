package com.alibou.security.service;


import org.springframework.scheduling.annotation.Async;

public interface MailService {
    @Async
    void sendEmailForSignUpConfirmation(String to, String verificationCode);

    @Async
    void sendEmailForForForgotPassword(String to, String verificationCode);
//
//    String textForConfirmationEmail(String confirmLink);
//
//    String textForForgotPassword(String confirmLink);


}
