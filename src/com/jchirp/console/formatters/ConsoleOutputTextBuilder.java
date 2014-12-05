package com.jchirp.console.formatters;

import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;

import java.util.Collections;
import java.util.List;

public class ConsoleOutputTextBuilder {


    private boolean isUserName = false;
    private boolean isContent = false;

    public ConsoleOutputTextBuilder withUserName(){
        isUserName = true;
        return this;
    }

    public ConsoleOutputTextBuilder withContent(){
        isContent = true;
        return this;
    }

    public String build(List<PostMsg> posts){
        String postsAsString = "";
        Collections.sort(posts, Collections.reverseOrder());
        for(PostMsg postMsg: posts) {
            postsAsString += buildUserName(postMsg) +
                             buildContent(postMsg) +
                             buildTimeSpanBetweenPostCreationAndNow(postMsg) + "\n";
        }
        return postsAsString;
    }

    private String buildUserName(PostMsg postMsg){
        return isUserName ? postMsg.getUserName() + " - " : "";
    }

    private String buildContent(PostMsg postMsg){
        return isContent ? postMsg.getContent() + " " : "";
    }

    private String buildTimeSpanBetweenPostCreationAndNow(PostMsg postMsg) {
        return new TimeSpan().timeSpanBetween(postMsg.getTimestamp(), Context.timestamp.now());
    }
}
