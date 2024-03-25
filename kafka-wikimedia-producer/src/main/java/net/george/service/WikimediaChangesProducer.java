package net.george.service;

import net.george.constant.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangesProducer {

    private final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void producerWikimediaChanges(String msg){
        try {
            kafkaTemplate.send(KafkaConstants.WIKIMEDIA_CHANGE_KAFKA_TOPIC, msg);
            logger.info("Kafka Produced Successfully");
        }catch (Exception e){
            logger.info("Kafka Producer failed");
        }
    }
}
