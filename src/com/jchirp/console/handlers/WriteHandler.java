package com.jchirp.console.handlers;

import com.jchirp.console.utils.Splitter;
import com.jchirp.core.messages.RequestMsg;

public class WriteHandler extends ConsoleInputHandlerImpl{

    public static final String ARROW = "->";

    @Override
    public String handleRequest(String consoleInput) {
        if (consoleInput.contains(ARROW)) {

            Splitter splitter = new Splitter();
            Splitter.Output output = splitter.splitUserNameFromContent(ARROW, consoleInput);
            if (!inputIsInvalid(output)) {
                RequestMsg request = new RequestMsg(output.getUserName(), output.getContent());
                post.execute(request);
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
