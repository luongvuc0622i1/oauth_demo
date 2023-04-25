package com.example.oauth2client.service;

public interface MailSender {

    void sendEmail(String to, String subject, String text);
}
