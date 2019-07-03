package com.axellageraldinc.rabbitmqproducer.service;

import com.axellageraldinc.rabbitmqproducer.model.Payload;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTestListener {
    public Payload payload;

    @RabbitListener(queues = "testQueue")
    public void listen(Payload payload) {
        this.payload = payload;
    }
}
