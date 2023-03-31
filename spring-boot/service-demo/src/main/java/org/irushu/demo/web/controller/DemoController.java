package org.irushu.demo.web.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.irushu.demo.service.MysqlService;
import org.irushu.demo.service.KafkaProducerService;
import org.irushu.demo.service.RedisCounterService;
import org.irushu.demo.web.model.DemoRequest;
import org.irushu.demo.web.model.DemoResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")

public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private MysqlService mysqlService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private RedisCounterService redisCounterService;

    @RequestMapping(value = "/00-echo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "An echo function that returns the input value.", description = "The value in output is the same as the value in input.", security = @SecurityRequirement(name = "bearerAuth"))
    public DemoResponse echo(@RequestBody DemoRequest demoRequest)
    {
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setOutput(demoRequest.getInput());
        return demoResponse;
    }

    @RequestMapping(value = "/01-mysql", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search data in Mysql DB.", description = "", security = @SecurityRequirement(name = "bearerAuth"))
    public DemoResponse mysql(@RequestBody DemoRequest demoRequest)
    {
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setOutput(mysqlService.findByInput(demoRequest.getInput()));
        return demoResponse;
    }

    @RequestMapping(value = "/02-kafka", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Send and Receive message through Kafka topic.", description = "", security = @SecurityRequirement(name = "bearerAuth"))
    public DemoResponse kafka(@RequestBody DemoRequest demoRequest)
    {
        kafkaProducerService.send(demoRequest);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setOutput("Message successfully sent to Kafka Topic");
        return demoResponse;
    }

    @RequestMapping(value = "/03-redis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Increment through Redis cache.", description = "", security = @SecurityRequirement(name = "bearerAuth"))
    public DemoResponse redis(@RequestBody DemoRequest demoRequest)
    {
        Long value = redisCounterService.count(demoRequest.getInput());
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setOutput(value.toString());
        return demoResponse;
    }
}
