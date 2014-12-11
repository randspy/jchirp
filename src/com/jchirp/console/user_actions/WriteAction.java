package com.jchirp.console.user_actions;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class WriteAction extends UserActionImpl {

    public static final String ARROW = "->";

    public WriteAction(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {
        if (consoleInput.contains(ARROW)) {
            Splitter.Output output = new Splitter().splitIntoTwoValues(ARROW, consoleInput);
            usecase.execute(new RequestMsg(output.getBeforeSplitElement(), output.getAfterSplitElement()));
        }
        else {
            return next_handler.handleRequest(consoleInput);
        }

        return "";
    }
}
