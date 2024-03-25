package net.george.config;

import org.apache.kafka.clients.admin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class KafkaTopicConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Autowired
    Admin admin;

    //create topic
//    @Bean
//    public NewTopic createTopic(){
//        return TopicBuilder.name("topic-1")
//                .build();
//    }

    public void createTopicIfNotExists(String topicName) throws ExecutionException, InterruptedException {
        AdminClient adminClient = admin.getAdminClient();
        ListTopicsResult topicsResult = adminClient.listTopics();
        Set<String> topics = topicsResult.names().get();
        if(!topics.contains(topicName)){
            logger.info(String.format("Kafka topic is not exists -- Creating the topic : %s",topicName));
            adminClient.createTopics(Stream.of(topicName).map(name -> new NewTopic(name,1,(short)1 )).collect(Collectors.toList()));
        }
    }
}
