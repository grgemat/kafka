package net.george.config;

import net.george.constant.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(KafkaConstants.WIKIMEDIA_CHANGE_KAFKA_TOPIC)
                .build();
    }
}
