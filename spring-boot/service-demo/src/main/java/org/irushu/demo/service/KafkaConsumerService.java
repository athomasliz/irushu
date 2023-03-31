package org.irushu.demo.service;

import org.irushu.demo.web.model.DemoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "topic.test", groupId="group1")
    public void consume(DemoRequest demoRequest, Message<DemoRequest> message)
    {
        logger.info(String.format("DemoRequest created -> %s", demoRequest));

        MessageHeaders headers = message.getHeaders();

        logger.info(String.format("Partition Id:%s | Received Timestamp: %s", headers.get(KafkaHeaders.RECEIVED_PARTITION),headers.get(KafkaHeaders.RECEIVED_TIMESTAMP)));
    }
}
