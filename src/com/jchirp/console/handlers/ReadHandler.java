package com.jchirp.console.handlers;

import com.jchirp.console.formatters.TimeSpan;
import com.jchirp.console.time.CurrentTime;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

import java.util.List;

public class ReadHandler extends ConsoleInputHandlerImpl {

    private final CurrentTime time;

    public ReadHandler(Command usecase, CurrentTime time) {
        this.usecase = usecase;
        this.time = time;
    }


    @Override
    public String handleRequest(String consoleInput) {

        RequestMsg requestMsg = new RequestMsg(consoleInput, "");
        ResponseMsg responseMsg = usecase.execute(requestMsg);
        if (responseMsg != null) {
            List<PostMsg> posts = responseMsg.posts();
            return  posts.get(0).getContent() + " " +
                    new TimeSpan().timeSpanBetween(posts.get(0).getTimestamp(), time.now());
        }

        return "";
    }
}
