package net.george;

import net.george.service.WikimediaEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaWikimediaProducer implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(KafkaWikimediaProducer.class, args);
    }

    @Autowired
    WikimediaEventService wikimediaEventService;

    @Override
    public void run(String... args) throws Exception {
        wikimediaEventService.triggerWikimediaEvent();
    }
}