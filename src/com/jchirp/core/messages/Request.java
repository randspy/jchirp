package com.jchirp.core.messages;

public class Request {

    private String content;
    private String userName;

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }
}
