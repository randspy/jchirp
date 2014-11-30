package com.jchirp.console.console_handlers;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class FollowHandler extends ConsoleInputHandlerImpl {

    public static final String FOLLOWS = "follows";

    public FollowHandler(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {

        if (consoleInput.contains(FOLLOWS))
        {
            Splitter.Output output = new Splitter().splitUserNameFromContent(FOLLOWS, consoleInput);
            usecase.execute(new RequestMsg(output.getBeforeSplitElement(), output.getAfterSplitElement()));
        }
        else
        {
            return next_handler.handleRequest(consoleInput);
        }

        return "";
    }
}
