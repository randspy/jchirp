package com.jchirp.core.usecases;

import com.jchirp.core.messages.Request;

public interface Command {
    void execute(Request request);
}
