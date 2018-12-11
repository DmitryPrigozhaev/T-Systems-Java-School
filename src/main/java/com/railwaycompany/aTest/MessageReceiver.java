//package com.railwaycompany.aTest;
//
//import javax.jms.JMSException;
//
//import com.railwaycompany.entities.Station;
//import com.railwaycompany.services.api.StationService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageReceiver {
//
//    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
//
//    private static final String ORDER_RESPONSE_QUEUE = "test-33333";
//
//    @Autowired
//    StationService stationService;
//
//
//    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
//    public void receiveMessage(final Message<String> message) throws JMSException {
//        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        MessageHeaders headers = message.getHeaders();
//        LOG.info("Application : headers received : {}", headers);
//
//        String response = message.getPayload();
//        LOG.info("Application : response received : {}", response);
//
//        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//    }
//}