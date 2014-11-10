package com.jchirp.core.messages;

public class Request {

    private final String userName;
    private final String content;

    public Request(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }
}
