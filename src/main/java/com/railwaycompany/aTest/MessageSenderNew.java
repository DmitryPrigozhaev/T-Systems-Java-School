package com.railwaycompany.aTest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class MessageSenderNew {

    private static final Logger logger = Logger.getLogger(MessageSenderNew.class);

    @Autowired
    ConnectionFactory connectionFactory;

    public void sendMessage(String val) {
        try (JMSContext context = connectionFactory.createContext()) {
            Topic topic = context.createTopic("jms.topic.srcTopic");
            Message message = context.createTextMessage(val);
            context.createProducer().send(topic, message);
        }
    }

}
