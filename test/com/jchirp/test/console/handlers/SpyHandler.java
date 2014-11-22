package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandlerImpl;

public class SpyHandler extends ConsoleInputHandlerImpl{

    private boolean wasCalled;

    public SpyHandler() {
        wasCalled = false;
    }

    @Override
    public void handleRequest(String consoleInput) {
        wasCalled = true;
    }

    public boolean wasCalled() {
        return wasCalled;
    }
}
