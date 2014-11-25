package com.jchirp;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.ReadHandler;
import com.jchirp.console.handlers.WriteHandler;
import com.jchirp.core.external.Context;
import com.jchirp.core.usecases.ReadPost;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.externals.CurrentTime;
import com.jchirp.externals.InMemoryGateway;

public class Main {

    public static void main(String[] args) {

        ConsoleInputHandler handler = buildIInfrastructure();

        while (true) {
            printPrompt();

            String input = System.console().readLine();

            if (doesExitProgram(input))
                break;

            System.out.print(handler.handleRequest(input));
        }
    }

    private static ConsoleInputHandler buildIInfrastructure() {
        Context.gateway = new InMemoryGateway();
        Context.timestamp = new CurrentTime();

        ConsoleInputHandler readHandler = new ReadHandler(new ReadPost());
        ConsoleInputHandler writeHandler = new WriteHandler(new WritePost());
        writeHandler.setNext(readHandler);

        return  writeHandler;
    }

    private static void printPrompt() {
        System.out.print("> ");
    }

    private static boolean doesExitProgram(String input) {
        return input.toUpperCase().equals("EXIT");
    }
}
