package com.jchirp.console.handlers;

public interface ConsoleInputHandler {
    public void setNext(ConsoleInputHandler handler);
    public String handleRequest(String consoleInput);
}
