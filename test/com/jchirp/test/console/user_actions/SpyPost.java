package com.jchirp.test.console.user_actions;

import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

public class SpyPost implements Command{
    private RequestMsg requestMsg;
    private ResponseMsg responseMsg;
    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {
        this.requestMsg = requestMsg;
        return responseMsg;
    }

    public RequestMsg getRequestMsg() {
        return requestMsg;
    }

    public void setResponseMsg(ResponseMsg responseMsg) {
        this.responseMsg = responseMsg;
    }
}
