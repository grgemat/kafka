package net.george.service;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangesHandler implements BackgroundEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private final WikimediaChangesProducer wikimediaChangesProducer;

    WikimediaChangesHandler (WikimediaChangesProducer wikimediaChangesProducer){
        this.wikimediaChangesProducer = wikimediaChangesProducer;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        String msg = messageEvent.getData();
        logger.info(String.format("Event Message -? %s", msg));
        wikimediaChangesProducer.producerWikimediaChanges(msg);
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
