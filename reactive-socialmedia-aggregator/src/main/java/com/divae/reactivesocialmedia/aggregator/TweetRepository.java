package com.divae.reactivesocialmedia.aggregator;

import com.divae.reactivesocialmedia.domain.Tweet;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Component
public class TweetRepository {

    public Flux<Tweet> findAll() {
        return Flux.just(new Tweet("sombrero83", "This is a test"),
                new Tweet("sombrero83", "This is a test2"),
                new Tweet("sombrero83", "This is a test3"))
                .delayElements(Duration.ofSeconds(2));
    }

    public Flux<Tweet> save(Flux<Tweet> tweets) {
        return null;
    }


}
