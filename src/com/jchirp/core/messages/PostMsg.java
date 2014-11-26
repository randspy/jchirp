package com.jchirp.core.messages;

import org.joda.time.DateTime;

public class PostMsg implements Comparable<PostMsg>{
    private final String userName;
    private final String content;
    private final DateTime timestamp;

    public PostMsg(String userName, String content, DateTime timestamp) {
        this.userName = userName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() { return content; }

    public DateTime getTimestamp() { return timestamp; }

    @Override
    public int compareTo(PostMsg postMsg) {
        return timestamp.compareTo(postMsg.timestamp);
    }
}
