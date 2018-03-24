package com.divae.reactivesocialmedia.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {

    private final String username;
    private final String text;

    @JsonCreator
    public Tweet(@JsonProperty("username") String username, @JsonProperty("text") String text) {
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "username='" + username + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
