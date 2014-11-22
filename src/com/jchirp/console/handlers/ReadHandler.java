package com.jchirp.console.handlers;

import com.jchirp.core.usecases.Command;

public class ReadHandler extends ConsoleInputHandlerImpl {

    public ReadHandler(Command usecase) {
        this.usecase = usecase;
    }

    @Override
    public String handleRequest(String consoleInput) {
        return "";
    }
}
