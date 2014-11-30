package com.jchirp.console.console_handlers;

import com.jchirp.console.formatters.ConsoleOutputTextBuilder;
import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

import java.util.List;

public class WallHandler extends ConsoleInputHandlerImpl {

    private static final String WALL = "wall";

    public WallHandler(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {

        if(consoleInput.contains(WALL))
        {
            Splitter.Output output = new Splitter().splitUserNameFromContent(WALL, consoleInput);
            ResponseMsg responseMsg = usecase.execute(new RequestMsg(output.getBeforeSplitElement(),""));
            return responseMsg != null ? formatPostsDisplayedToUser(responseMsg.posts()) : "";
        }
        else
        {
            return next_handler.handleRequest(consoleInput);
        }
    }

    private String formatPostsDisplayedToUser(List<PostMsg> posts) {
        return new ConsoleOutputTextBuilder().withUserName().withContent().build(posts);
    }
}
