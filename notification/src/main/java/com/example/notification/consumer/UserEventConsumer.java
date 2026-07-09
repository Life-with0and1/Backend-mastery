package com.example.notification.consumer;

import com.example.notification.dto.UserRegisterEvent;
import com.example.notification.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    private final EmailService emailService;

    public UserEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }


    @KafkaListener(topics = "user-registered", groupId = "notification-group")
    public void consume(UserRegisterEvent event) {
        emailService.sendWelcomeEmail(event);
    }
}