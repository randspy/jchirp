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
                             buildTimeSpanBetweenPostAndNow(postMsg) + "\n";
        }
        return postsAsString;
    }

    private String buildUserName(PostMsg postMsg){
        if (isUserName) {
            return postMsg.getUserName() + " - ";
        }
        return "";
    }

    private String buildContent(PostMsg postMsg){
        if(isContent) {
            return postMsg.getContent() + " ";
        }
        return "";
    }

    private String buildTimeSpanBetweenPostAndNow(PostMsg postMsg) {
        return new TimeSpan().timeSpanBetween(postMsg.getTimestamp(), Context.timestamp.now());
    }
}
