package com.jchirp.console.user_actions;

import com.jchirp.core.usecases.Command;

public abstract class ActionImpl implements Action {
    protected Action next_handler;
    protected Command usecase;

    public ActionImpl(Command usecase) {
        this.usecase = usecase;
    }

    @Override
    public void setNext(Action handler) {
        this.next_handler = handler;
    }

    public abstract String handleRequest(String consoleInput);
}
