package com.jchirp;

import com.jchirp.console.handlers.ReadHandler;
import com.jchirp.console.handlers.WriteHandler;
import com.jchirp.core.external.Context;
import com.jchirp.core.usecases.ReadPost;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.externals.CurrentTime;
import com.jchirp.externals.InMemoryGateway;

public class Main {

    public static void main(String[] args) {

        CurrentTime timestamp = new CurrentTime();
        Context.gateway = new InMemoryGateway();
        Context.timestamp = timestamp;

        ReadHandler readHandler = new ReadHandler(new ReadPost(), timestamp);
        WriteHandler writeHandler = new WriteHandler(new WritePost());
        writeHandler.setNext(readHandler);


        while (true) {
            System.out.print("> ");
            String input = System.console().readLine();
            if (equalsExit(input))
                break;
            System.out.print(writeHandler.handleRequest(input));
        }
    }

    private static boolean equalsExit(String input) {
        return input.toUpperCase().equals("EXIT");
    }
}
