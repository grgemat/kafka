package net.george.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    private final static Logger logger = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);
    @KafkaListener(topics = "wikimedia_change", groupId = "wikimedia-grp-1")
    public void consumeWikimediaChanges(String msg){
        logger.info(String.format("Consumer --> %s",msg));
    }
}
