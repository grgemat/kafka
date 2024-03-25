package net.george.service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaEventService{

    private final WikimediaChangesProducer wikimediaChangesProducer;

    WikimediaEventService(WikimediaChangesProducer wikimediaChangesProducer){
        this.wikimediaChangesProducer = wikimediaChangesProducer;
    }
    public void triggerWikimediaEvent() throws InterruptedException {
        BackgroundEventHandler backgroundEventHandler = new WikimediaChangesHandler(wikimediaChangesProducer);
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(URI.create("https://stream.wikimedia.org/v2/stream/recentchange"));
        BackgroundEventSource.Builder backgroundEventSourceBuilder = new BackgroundEventSource.Builder(backgroundEventHandler, eventSourceBuilder);
        BackgroundEventSource eventBuilder = backgroundEventSourceBuilder.build();
        eventBuilder.start();
        TimeUnit.SECONDS.sleep(30);

    }
}
