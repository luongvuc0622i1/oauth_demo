package com.example.demooauth2.service;

import com.example.demooauth2.model.User;

public interface MailSender {

    void sendEmail(String to,String subject,String text);


}
