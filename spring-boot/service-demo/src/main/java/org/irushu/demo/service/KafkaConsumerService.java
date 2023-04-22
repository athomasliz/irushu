package org.irushu.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.irushu.demo.web.model.DemoRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "topic.test", groupId="group1")
    public void consume(DemoRequest demoRequest, Message<DemoRequest> message)
    {
        log.info(String.format("DemoRequest created -> %s", demoRequest));

        MessageHeaders headers = message.getHeaders();

        log.info(String.format("Partition Id:%s | Received Timestamp: %s", headers.get(KafkaHeaders.RECEIVED_PARTITION),headers.get(KafkaHeaders.RECEIVED_TIMESTAMP)));
    }
}
