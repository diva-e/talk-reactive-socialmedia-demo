package com.divae.reactivesocialmedia.domain;

public class Tweet {

    private final String username;
    private final String text;

    public Tweet(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

}
