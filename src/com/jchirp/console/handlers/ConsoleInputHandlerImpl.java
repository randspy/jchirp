package com.jchirp.console.handlers;

import com.jchirp.core.usecases.Command;

public abstract class ConsoleInputHandlerImpl implements ConsoleInputHandler {
    protected ConsoleInputHandler next_handler;
    protected Command usecase;

    @Override
    public void setNext(ConsoleInputHandler handler) {
        this.next_handler = handler;
    }

    public abstract String handleRequest(String consoleInput);
}
