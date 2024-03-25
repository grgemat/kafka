package net.george.service;


import net.george.config.KafkaTopicConfig;
import net.george.dto.UserDetailsDTO;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaProdcuer {


    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTopicConfig kafkaTopicConfig;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(KafkaProdcuer.class);

    public KafkaProdcuer(KafkaTemplate<String, String> kafkaTemplate, KafkaTopicConfig kafkaTopicConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicConfig = kafkaTopicConfig;
    }

    public void produceMessage(String msg){
        kafkaTemplate.send("topic-1", msg);
        logger.info(String.format("Message Sent %s", msg));
    }

    public String publishUserDetails(UserDetailsDTO userDetailsDTO) throws ExecutionException, InterruptedException {
        Message<UserDetailsDTO> userDetailsDTOMessage = MessageBuilder
                .withPayload(userDetailsDTO)
                .setHeader(KafkaHeaders.TOPIC, "topic-2")
                .build();
        kafkaTopicConfig.createTopicIfNotExists("topic-2");
        kafkaTemplate.send(userDetailsDTOMessage);
        return "Message published to kafka";
    }
}
