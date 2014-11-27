package com.jchirp.console.console_handlers;

/*
 * Interface for a chain of responsibility pattern
 */
public interface ConsoleInputHandler {
    public void setNext(ConsoleInputHandler handler);
    public String handleRequest(String consoleInput);
}
