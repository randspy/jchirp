package com.jchirp.core.usecases;

import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

public interface Command {
    ResponseMsg execute(RequestMsg requestMsg);
}
