package org.irushu.demo.service;

import org.irushu.demo.web.model.DemoRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService {

    private final KafkaTemplate<String, DemoRequest> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, DemoRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(DemoRequest demoRequest){
        kafkaTemplate.send("topic.test", demoRequest);
    }
}
