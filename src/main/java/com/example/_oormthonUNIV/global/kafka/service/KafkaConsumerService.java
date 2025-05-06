package com.example._oormthonUNIV.global.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-registration", groupId = "user-event-consumer")
    public void processUserRegistrationMessage(String message) {
        System.out.println("Received user registration message: " + message);
    }
}