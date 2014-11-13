package com.jchirp.core.usecases;

import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

public class ReadPost implements Command {
    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {
        return new ResponseMsg();
    }
}
