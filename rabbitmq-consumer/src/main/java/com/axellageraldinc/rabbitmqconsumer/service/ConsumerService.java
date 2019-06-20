package com.axellageraldinc.rabbitmqconsumer.service;

import com.axellageraldinc.rabbitmqconsumer.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "${queue.A}")
    public void handleQueueAMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue A : " + payload.getMessage());
    }

    @RabbitListener(queues = "${queue.B}")
    public void handleQueueBMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue B : " + payload.getMessage());
    }

    @RabbitListener(queues = "${queue.C}")
    public void handleQueueCMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue C : " + payload.getMessage());
    }

    @RabbitListener(queues = "${queue.D}")
    public void handleQueueDMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue D : " + payload.getMessage());
    }

    @RabbitListener(queues = "${queue.E}")
    public void handleQueueEMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue E : " + payload.getMessage());
    }

    @RabbitListener(queues = "${queue.F}")
    public void handleQueueFMessageReception(Payload payload) {
        LOGGER.info("Message received in Queue F : " + payload.getMessage());
    }
}
