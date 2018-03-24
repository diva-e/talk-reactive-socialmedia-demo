package com.divae.reactivesocialmedia.client;

import com.divae.reactivesocialmedia.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class SocialMediaStreamingClient {

    private static final Logger LOG = LoggerFactory.getLogger(SocialMediaStreamingClient.class);

    @PostConstruct
    public void consumeTweetsInRealtime() {
        WebClient aggregatorClient = WebClient.create("http://localhost:8082");
        aggregatorClient.get().uri("/tweets")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Tweet.class)
                .doOnNext(tweet -> LOG.info("Received new tweet: {}", tweet))
                .log()
                .blockLast();
    }

}
