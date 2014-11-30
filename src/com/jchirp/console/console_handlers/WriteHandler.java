package com.jchirp.console.console_handlers;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class WriteHandler extends ConsoleInputHandlerImpl{

    public static final String ARROW = "->";

    public WriteHandler(Command usecase) {
        super(usecase);
    }

    @Override
    public String handleRequest(String consoleInput) {
        if (consoleInput.contains(ARROW)) {
            Splitter.Output output = new Splitter().splitUserNameFromContent(ARROW, consoleInput);
            usecase.execute(new RequestMsg(output.getUserName(), output.getContent()));
        }
        else {
            return next_handler.handleRequest(consoleInput);
        }

        return "";
    }
}
