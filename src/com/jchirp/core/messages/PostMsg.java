package com.jchirp.core.messages;

public class PostMsg {
    private final String userName;

    public PostMsg(String userName) {
        this.userName = userName;
    }

    public String userName() {
        return userName;
    }
}
