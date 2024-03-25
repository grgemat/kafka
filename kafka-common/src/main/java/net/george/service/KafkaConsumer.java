package net.george.service;

import net.george.dto.UserDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "topic-1", groupId = "myGroup")
    public void consumeTopic1(String msg){
        logger.info(String.format("Kafka consumed ---------> %s", msg ));
    }

    @KafkaListener(topics = "topic-2", groupId = "myGroup")
    public void consumeTopic2(UserDetailsDTO msg){
        logger.info("Kafka consumed ---------" );
        logger.info(String.format("Name : %s", msg.getName()));
    }
}
