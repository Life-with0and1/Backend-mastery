package com.example.notification.service;

import com.example.notification.dto.UserRegisterEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(UserRegisterEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ayushkumarit01@gmail.com");
        message.setTo(event.getEmail());

        message.setSubject("Welcome to Backend Mastery");

        message.setText("Hi " + event.getName() + ",\n\n" + "Your account has been created successfully.\n\n" + "Welcome!");

        mailSender.send(message);
    }
}