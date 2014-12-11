package com.jchirp.console.user_actions;

/*
 * Interface for a chain of responsibility pattern
 */
public interface UserAction {
    public void setNext(UserAction handler);
    public String handleRequest(String consoleInput);
}
