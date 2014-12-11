package com.jchirp.console.user_actions;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class FollowAction extends UserActionImpl {

    public static final String FOLLOWS = "follows";

    public FollowAction(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {

        if (consoleInput.contains(FOLLOWS))
        {
            Splitter.Output output = new Splitter().splitIntoTwoValues(FOLLOWS, consoleInput);
            usecase.execute(new RequestMsg(output.getBeforeSplitElement(), output.getAfterSplitElement()));
        }
        else
        {
            return next_handler.handleRequest(consoleInput);
        }

        return "";
    }
}
