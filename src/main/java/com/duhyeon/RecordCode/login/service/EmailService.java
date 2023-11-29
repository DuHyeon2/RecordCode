package com.duhyeon.RecordCode.login.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String text) throws MessagingException;
}
