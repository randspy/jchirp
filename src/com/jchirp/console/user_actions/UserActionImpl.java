package com.jchirp.console.user_actions;

import com.jchirp.core.usecases.Command;

public abstract class UserActionImpl implements UserAction {
    protected UserAction next_handler;
    protected Command usecase;

    public UserActionImpl(Command usecase) {
        this.usecase = usecase;
    }

    @Override
    public void setNext(UserAction handler) {
        this.next_handler = handler;
    }

    public abstract String handleRequest(String consoleInput);
}
