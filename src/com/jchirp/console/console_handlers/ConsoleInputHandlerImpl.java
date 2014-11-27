package com.jchirp.console.console_handlers;

import com.jchirp.core.usecases.Command;

public abstract class ConsoleInputHandlerImpl implements ConsoleInputHandler {
    protected ConsoleInputHandler next_handler;
    protected Command usecase;

    public ConsoleInputHandlerImpl(Command usecase) {
        this.usecase = usecase;
    }

    @Override
    public void setNext(ConsoleInputHandler handler) {
        this.next_handler = handler;
    }

    public abstract String handleRequest(String consoleInput);
}
