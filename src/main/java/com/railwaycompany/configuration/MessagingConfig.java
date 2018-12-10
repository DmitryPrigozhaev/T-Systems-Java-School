package com.railwaycompany.configuration;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfig {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private static final String ORDER_QUEUE = "test-33333";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){

        System.out.println(" * * *    В ActiveMQConnectionFactory    * * * ");

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("railwaycompany"));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){

        System.out.println(" * * *    В JmsTemplate    * * * ");

        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(ORDER_QUEUE);
        return template;
    }

}