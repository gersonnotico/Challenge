package com.calculator.math.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String nameRow, Object message){
        this.rabbitTemplate.convertAndSend(nameRow, message);
    }
}
