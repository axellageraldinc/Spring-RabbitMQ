package com.axellageraldinc.rabbitmqproducer.configuration;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

@Configuration
@EnableRabbit
public class RabbitMQProducerTestConfiguration implements RabbitListenerConfigurer {

    @Bean
    public TestRabbitTemplate testRabbitTemplate() {
        TestRabbitTemplate testRabbitTemplate = new TestRabbitTemplate(testConnectionFactory());
        testRabbitTemplate.setMessageConverter(testMessageConverter());
        return testRabbitTemplate;
    }

    @Bean
    public MessageConverter testMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory testConnectionFactory() {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        Connection connection = mock(Connection.class);
        Channel channel = mock(Channel.class);
        willReturn(connection).given(factory).createConnection();
        willReturn(channel).given(connection).createChannel(anyBoolean());
        given(channel.isOpen()).willReturn(true);
        return factory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(testConnectionFactory());
        return factory;
    }

    @Bean
    DirectExchange testDirectExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Queue testQueue() {
        return new Queue("testQueue");
    }

    @Bean
    Binding testBinding(DirectExchange testDirectExchange, Queue testQueue) {
        return BindingBuilder.bind(testQueue).to(testDirectExchange).withQueueName();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(testMessageHandlerMethodFactory());
    }

    @Bean
    public MessageHandlerMethodFactory testMessageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        defaultMessageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
        return defaultMessageHandlerMethodFactory;
    }
}
