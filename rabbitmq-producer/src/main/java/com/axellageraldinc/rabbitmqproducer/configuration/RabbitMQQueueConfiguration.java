package com.axellageraldinc.rabbitmqproducer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
    @Value("${queue.A}")
    private String queueA;

    @Value("${queue.B}")
    private String queueB;

    @Value("${queue.C}")
    private String queueC;

    @Value("${queue.D}")
    private String queueD;

    @Value("${queue.E}")
    private String queueE;

    @Value("${queue.F}")
    private String queueF;

    @Bean
    public Queue queueA() {
        return new Queue(queueA);
    }

    @Bean
    public Queue queueB() {
        return new Queue(queueB);
    }

    @Bean
    public Queue queueC() {
        return new Queue(queueC);
    }

    @Bean
    public Queue queueD() {
        return new Queue(queueD);
    }

    @Bean
    public Queue queueE() {
        return new Queue(queueE);
    }

    @Bean
    public Queue queueF() {
        return new Queue(queueF);
    }
}
