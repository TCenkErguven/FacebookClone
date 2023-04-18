package com.cenk.rabbitmq.producer;

import com.cenk.rabbitmq.model.CreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSendData(CreateUserModel model){
        rabbitTemplate.convertAndSend("auth-exchange",
                "auth-routing-key-create-user",model);
    }
}
