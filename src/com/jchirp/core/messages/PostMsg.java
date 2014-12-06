package com.jchirp.core.messages;

import org.joda.time.DateTime;

public class PostMsg implements Comparable<PostMsg>{
    private String userName = "";
    private String content = "";
    private DateTime timestamp = new DateTime();
    private DateTime currentTime = new DateTime();

    public static class Builder{
        private String userName;
        private String content;
        private DateTime timestamp;
        private DateTime currentTime;

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withTimestamp(DateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withCurrentTime(DateTime currentTime) {
            this.currentTime = currentTime;
            return this;
        }

        public PostMsg build() {
            return new PostMsg(this);
        }
    }

    public PostMsg(Builder post) {
        userName = post.userName;
        content = post.content;
        timestamp = post.timestamp;
        currentTime = post.currentTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() { return content; }

    public DateTime getPostTimestamp() { return timestamp; }

    public DateTime getCurrentTime() {
        return currentTime;
    }

    @Override
    public int compareTo(PostMsg postMsg) {
        return timestamp.compareTo(postMsg.timestamp);
    }
}
