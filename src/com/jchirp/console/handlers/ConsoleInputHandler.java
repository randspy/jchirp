package com.jchirp.console.handlers;

import com.jchirp.core.usecases.Command;

public interface ConsoleInputHandler {
    public void setNext(ConsoleInputHandler handler);
    public void handleRequest(String consoleInput);
    public void setUsecase(Command post);
}