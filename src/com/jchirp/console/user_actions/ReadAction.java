package com.jchirp.console.user_actions;

import com.jchirp.console.formatters.ConsoleOutputTextBuilder;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

import java.util.List;

public class ReadAction extends ActionImpl {

    public ReadAction(Command usecase) {
        super(usecase);
    }

    @Override
    protected String handleAction(String consoleInput) {
        ResponseMsg responseMsg = usecase.execute(new RequestMsg(consoleInput, ""));
        return responseMsg != null ? formatPostsDisplayedToUser(responseMsg.posts()) : "";
    }

    @Override
    protected String getActionToken() {
        return "";
    }

    private String formatPostsDisplayedToUser(List<PostMsg> posts) {
        return new ConsoleOutputTextBuilder().withContent().build(posts);
    }
}
