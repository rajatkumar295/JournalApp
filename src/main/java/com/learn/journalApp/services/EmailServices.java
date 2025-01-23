package com.learn.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String message){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(message);
            javaMailSender.send(mail);
        } catch (Exception e) {
            log.error("Exception while sendMail :", e);
        }
    }
}
