package com.jchirp.console.user_actions;

/*
 * Interface for a chain of responsibility pattern
 */
public interface Action {
    public void setNext(Action handler);
    public String handleRequest(String consoleInput);
}
