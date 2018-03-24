package com.divae.reactivesocialmedia.twitterimport;

import com.divae.reactivesocialmedia.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TweetImporter {

    private static final Logger logger = LoggerFactory.getLogger(TweetImporter.class);

    @PostConstruct
    public void generateTweetsAndSendThemToAggregator() {
        Flux<Tweet> generatedTweets = Flux.interval(Duration.ofSeconds(1))
                .map(tweet -> generateRandomTweet())
                .log();

        WebClient aggregatorClient = WebClient.create("http://localhost:8082");
        aggregatorClient.post().uri("/tweets")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(generatedTweets, Tweet.class)
                .retrieve()
                .bodyToMono(Void.class)
                .log()
                .block();
    }

    private Tweet generateRandomTweet() {
        String username = "sombrero83";
        String text = "bla fasel " + LocalDateTime.now();
        return new Tweet(username, text);
    }

}
