package com.axellageraldinc.rabbitmqproducer.service;

import com.axellageraldinc.rabbitmqproducer.model.Payload;

public interface ProducerService {
    void sendToDirectExchange(Payload payload, String routingKey);

    void sendToTopicExchange(Payload payload, String topic);

    void sendToFanoutExchange(Payload payload);
}
