package com.alibou.security.service.impl;

import com.alibou.security.config.MessageByLang;
import com.alibou.security.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final static Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    //    @Value("${spring.mail.username}")
    String sender = "DoctorS.Med.Fazo@gmail.com";

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.iPAndPort}")
    String confirmLinkIPAndPort;

    @Value("${spring.mail.conformEmailForSignUpURL}")
    String conformEmailForSignUpURL;

    @Value("${spring.mail.conformEmailForResetForgottenPasswordURL}")
    private String conformEmailForResetForgottenPasswordURL;


    @Async
    @Override
    public void sendEmailForSignUpConfirmation(String to, String link) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("subject");
        String href = confirmLinkIPAndPort + conformEmailForSignUpURL + link;
        String text = MessageByLang.getMessage("TEXT_OF_EMAIL") + "\n" + href;
        simpleMailMessage.setText(text);
        Thread thread = new Thread(() -> mailSender.send(simpleMailMessage));
        thread.start();
    }

    @Override
    public void sendEmailForForForgotPassword(String to, String verificationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("subject");
        String href = confirmLinkIPAndPort + conformEmailForResetForgottenPasswordURL + verificationCode;
        String text = MessageByLang.getMessage("TEXT_OF_EMAIL") + "\n" + href;
        simpleMailMessage.setText(text);
        Thread thread = new Thread(() -> mailSender.send(simpleMailMessage));
        thread.start();
    }

}
