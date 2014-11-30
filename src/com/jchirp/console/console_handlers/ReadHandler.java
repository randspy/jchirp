package com.jchirp.console.console_handlers;

import com.jchirp.console.formatters.ConsoleOutputTextBuilder;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

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
        return new ConsoleOutputTextBuilder().withContent().build(posts);
    }
}
