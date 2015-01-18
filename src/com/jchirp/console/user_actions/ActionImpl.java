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

    @Override
    public String handleRequest(String consoleInput) {

        if(consoleInput.contains(getActionToken()))
        {
            return  handleAction(consoleInput);
        }
        else
        {
            return next_handler.handleRequest(consoleInput);
        }
    }

    protected abstract String handleAction(String consoleInput);

    protected abstract String getActionToken();

}
