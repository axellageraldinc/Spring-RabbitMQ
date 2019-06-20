package com.axellageraldinc.rabbitmqproducer.web;

import com.axellageraldinc.rabbitmqproducer.model.Payload;
import com.axellageraldinc.rabbitmqproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/producer")
public class ProducerRestController {
    @Autowired
    private ProducerService producerService;

    @PostMapping(
            value = "/direct/{routingKey}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToDirectExchange(@PathVariable(value = "routingKey") String routingKey,
                                               @RequestBody Payload payload) {
        producerService.sendToDirectExchange(payload, routingKey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(
            value = "/topic/{topic}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToTopicExchange(@PathVariable(value = "topic") String topic,
                                              @RequestBody Payload payload) {
        producerService.sendToTopicExchange(payload, topic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(
            value = "/fanout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToFanoutExchange(@RequestBody Payload payload) {
        producerService.sendToFanoutExchange(payload);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
