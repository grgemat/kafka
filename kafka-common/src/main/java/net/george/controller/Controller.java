package net.george.controller;

import net.george.config.KafkaTopicConfig;
import net.george.dto.UserDetailsDTO;
import net.george.service.KafkaProdcuer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/v1/kafka")
public class Controller {

    private KafkaProdcuer kafkaProdcuer;
    private KafkaTopicConfig kafkaTopicConfig;


    public Controller(KafkaProdcuer kafkaProdcuer, KafkaTopicConfig kafkaTopicConfig) {
        this.kafkaProdcuer = kafkaProdcuer;
        this.kafkaTopicConfig = kafkaTopicConfig;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishKafka(@RequestParam String msg){
        kafkaProdcuer.produceMessage(msg);
        return ResponseEntity.ok("Message published to kafka");
    }
    @PostMapping("/publish-user")
    public ResponseEntity<String> publishUserDetails(@RequestBody UserDetailsDTO userDetailsDTO){
        try {
            kafkaProdcuer.publishUserDetails(userDetailsDTO);
            return ResponseEntity.ok("Message published to kafka");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Message publish failed !!");
        }

    }
}
