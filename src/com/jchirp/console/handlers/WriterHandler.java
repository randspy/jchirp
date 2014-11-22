package com.jchirp.console.handlers;

import com.jchirp.console.utils.Splitter;
import com.jchirp.core.messages.RequestMsg;

public class WriterHandler extends ConsoleInputHandlerImpl{

    public static final String ARROW = "->";

    @Override
    public void handleRequest(String consoleInput) {
        if (consoleInput.contains(ARROW)) {

            Splitter splitter = new Splitter();
            Splitter.Output output = splitter.splitUserNameFromContent("->", consoleInput);
            if (output.getUserName().isEmpty() || output.getContent().isEmpty())
                return;
            RequestMsg request = new RequestMsg(output.getUserName(), output.getContent());
            post.execute(request);
        }
        else {
            next_handler.handleRequest(consoleInput);
        }
    }
}
