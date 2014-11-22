package com.jchirp.test.console.handlers;

import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;

public class SpyWritePost implements Command{
    private RequestMsg request;

    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {
        request = requestMsg;
        return null;
    }

    public RequestMsg getRequest() {
        return request;
    }
}
