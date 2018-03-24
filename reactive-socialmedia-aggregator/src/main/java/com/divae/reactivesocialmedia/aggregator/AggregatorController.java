package com.divae.reactivesocialmedia.aggregator;

import com.divae.reactivesocialmedia.domain.Tweet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AggregatorController {

    private TweetRepository tweetRepository;

    public AggregatorController(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @GetMapping(path = "/tweets")
    public Flux<Tweet> getTweets() {
        return this.tweetRepository.findAll().log();
    }

    @PostMapping(path="/tweets", consumes = "application/stream+json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveTweets(@RequestBody Flux<Tweet> tweets) {
        return tweetRepository.save(tweets).then();
    }

}
