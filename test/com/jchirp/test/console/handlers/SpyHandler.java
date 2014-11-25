package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandlerImpl;

public class SpyHandler extends ConsoleInputHandlerImpl{

    private boolean wasCalled;

    public SpyHandler() {
        super(null);
        wasCalled = false;
    }

    @Override
    public String handleRequest(String consoleInput) {
        wasCalled = true;
        return "";
    }

    public boolean wasCalled() {
        return wasCalled;
    }
}
