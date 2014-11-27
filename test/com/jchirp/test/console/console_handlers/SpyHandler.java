package com.jchirp.test.console.console_handlers;

import com.jchirp.console.console_handlers.ConsoleInputHandlerImpl;

public class SpyHandler extends ConsoleInputHandlerImpl{

    private static final String SPY_HANDLER = "SpyHandler";
    private boolean wasCalled;

    public SpyHandler() {
        super(null);
        wasCalled = false;
    }

    @Override
    public String handleRequest(String consoleInput) {
        wasCalled = true;
        return SPY_HANDLER;
    }

    public boolean wasCalled() {
        return wasCalled;
    }

    public String getSpyHandlerResponse(){
        return SPY_HANDLER;
    }
}
