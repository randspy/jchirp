package com.jchirp.console.handlers;

import com.jchirp.console.formatters.TimeSpan;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

import java.util.Collections;
import java.util.List;

public class ReadHandler extends ConsoleInputHandlerImpl {

    public ReadHandler(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {
        ResponseMsg responseMsg = usecase.execute(new RequestMsg(consoleInput, ""));
        return responseMsg != null ? formatPostsDisplayedToUser(responseMsg.posts()) : "";
    }

    private String formatPostsDisplayedToUser(List<PostMsg> posts) {

        String postsAsString = "";
        Collections.reverse(posts);
        for(PostMsg postMsg: posts) {
            postsAsString += postMsg.getContent() + " " +
                             timeSpanBetweenPostAndNow(postMsg) + "\n";
        }
        return postsAsString;
    }

    private String timeSpanBetweenPostAndNow(PostMsg postMsg) {
        return new TimeSpan().timeSpanBetween(postMsg.getTimestamp(), Context.timestamp.now());
    }
}
