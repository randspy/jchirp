package com.jchirp.core.messages;

public class RequestMsg {

    private final String userName;
    private final String content;

    public RequestMsg(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }
    public String getContent() { return content; }
}
