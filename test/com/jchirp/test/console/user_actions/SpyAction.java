package com.jchirp.test.console.user_actions;

import com.jchirp.console.user_actions.ActionImpl;

public class SpyAction extends ActionImpl {

    private static final String SPY_HANDLER = "SpyHandler";
    private boolean wasCalled;

    public SpyAction() {
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

    public String getSpyHandlerResponse(){ return SPY_HANDLER; }
}
