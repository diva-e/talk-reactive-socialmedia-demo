package com.divae.reactivesocialmedia.aggregator;

import com.divae.reactivesocialmedia.domain.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public interface TweetRepository extends ReactiveMongoRepository<Tweet, Long> {

    @Tailable
    Flux<Tweet> findTweetsBy();

}
