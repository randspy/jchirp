package com.jchirp.console.handlers;

import com.jchirp.console.formatters.Splitter;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;

public class WriteHandler extends ConsoleInputHandlerImpl{

    public static final String ARROW = "->";

    public WriteHandler(Command usecase) {
        this.usecase = usecase;
    }

    @Override
    public String handleRequest(String consoleInput) {
        if (consoleInput.contains(ARROW)) {

            Splitter.Output output = new Splitter().splitUserNameFromContent(ARROW, consoleInput);
            if (!inputIsInvalid(output)) {
                RequestMsg request = new RequestMsg(output.getUserName(), output.getContent());
                usecase.execute(request);
            }
        }
        else {
            return next_handler.handleRequest(consoleInput);
        }

        //there nothing to return when we write to usecase, so we do not case about the output
        return "";
    }

    private boolean inputIsInvalid(Splitter.Output output) {
        return output.getUserName().isEmpty() || output.getContent().isEmpty();
    }
}
