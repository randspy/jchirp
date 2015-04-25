package com.jchirp.console.formatters;

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
                             buildTimeSpanBetweenPostCreationAndThisMoment(postMsg) +
                             buildTextEnd();
        }
        return postsAsString;
    }

    private String buildUserName(PostMsg postMsg) {
        return isUserName ? postMsg.getUserName() + " - " : "";
    }

    private String buildContent(PostMsg postMsg) {
        return isContent ? postMsg.getContent() + " " : "";
    }

    private String buildTimeSpanBetweenPostCreationAndThisMoment(PostMsg postMsg) {
        return new TimeForDisplay().timeSpanBetween(postMsg.getPostTimestamp(), postMsg.getCurrentTime());
    }

    private String buildTextEnd(){
        return "\n";
    }
}
