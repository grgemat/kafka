package net.george;


import org.apache.kafka.streams.KafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaStreamSpringBootApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamSpringBootApplication.class, args);
    }

    @Autowired
    KafkaStreamDemo kafkaStreamDemo;

    @Override
    public void run(String... args) throws Exception {
        kafkaStreamDemo.streamKafka();
    }
}