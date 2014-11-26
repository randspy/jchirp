package com.jchirp.console.handlers;

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
            if (!inputIsInvalid(output)) {
                RequestMsg request = new RequestMsg(output.getUserName(), output.getContent());
                usecase.execute(request);
            }
        }
        else
        {
            return next_handler.handleRequest(consoleInput);
        }

        //there nothing to return when we have a follow usecase, so we do not case about the output
        return "";
    }

    private boolean inputIsInvalid(Splitter.Output output) {
        return output.getUserName().isEmpty() || output.getContent().isEmpty();
    }
}