package com.example.licenta.service.impl;

import com.example.licenta.model.EmailDetails;
import com.example.licenta.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMail(EmailDetails details) {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMessage());
        mailMessage.setSubject(details.getSubject());

        javaMailSender.send(mailMessage);
    }
}
