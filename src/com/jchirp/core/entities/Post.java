package com.jchirp.core.entities;

import org.joda.time.DateTime;

public class Post {

    private final String content;
    private final DateTime timestamp;

    public Post(String content, DateTime timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public DateTime getTimestamp() { return timestamp; }
}
