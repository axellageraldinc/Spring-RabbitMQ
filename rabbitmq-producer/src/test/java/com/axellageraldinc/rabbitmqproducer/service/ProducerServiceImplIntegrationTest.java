package com.axellageraldinc.rabbitmqproducer.service;

import com.axellageraldinc.rabbitmqproducer.configuration.RabbitMQProducerTestConfiguration;
import com.axellageraldinc.rabbitmqproducer.model.Payload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitMQProducerTestConfiguration.class, RabbitMQTestListener.class})
public class ProducerServiceImplIntegrationTest {
    @Autowired
    private TestRabbitTemplate testRabbitTemplate;
    @Autowired
    private RabbitMQTestListener rabbitMQTestListener;

    @Test
    public void sendToDirectExchange_Success() {
        testRabbitTemplate.convertAndSend("directExchange", "testQueue", new Payload("test"));

        assertThat(rabbitMQTestListener.payload.getMessage(), equalTo("test"));
    }
}