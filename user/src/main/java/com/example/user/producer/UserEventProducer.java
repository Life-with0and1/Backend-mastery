package com.example.user.producer;

import com.example.user.dto.UserRegisterEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {

    private final KafkaTemplate<String, UserRegisterEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserRegisterEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserRegister(UserRegisterEvent event){
        kafkaTemplate.send("user-registered", event);
    }
}
