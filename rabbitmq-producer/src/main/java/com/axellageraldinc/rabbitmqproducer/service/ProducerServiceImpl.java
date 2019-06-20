package com.axellageraldinc.rabbitmqproducer.service;

import com.axellageraldinc.rabbitmqproducer.model.Payload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${exchange.direct}")
    private String directExchange;

    @Value("${exchange.topic}")
    private String topicExchange;

    @Value("${exchange.fanout}")
    private String fanoutExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendToDirectExchange(Payload payload, String routingKey) {
        rabbitTemplate.convertAndSend(directExchange, routingKey, payload);
    }

    @Override
    public void sendToTopicExchange(Payload payload, String topic) {
        rabbitTemplate.convertAndSend(topicExchange, topic, payload);
    }

    @Override
    public void sendToFanoutExchange(Payload payload) {
        rabbitTemplate.convertAndSend(fanoutExchange, "", payload); // routingKey is ignored
    }
}
