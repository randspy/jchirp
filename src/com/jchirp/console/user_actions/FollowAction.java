package com.jchirp.console.user_actions;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class FollowAction extends ActionImpl {

    public static final String FOLLOWS = "follows";

    public FollowAction(Command usecase) {
        super(usecase);
    }

    @Override
    protected String handleAction(String consoleInput) {
        Splitter.Output output = new Splitter().splitIntoTwoValues(FOLLOWS, consoleInput);
        usecase.execute(new RequestMsg(output.getElementBeforeSplitToken(),
                                       output.getElementsAfterSplitToken()));
        return "";
    }

    @Override
    protected String getActionToken() {
        return FOLLOWS;
    }
}
