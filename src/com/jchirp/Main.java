package com.jchirp;

import com.jchirp.console.user_actions.*;
import com.jchirp.core.external.Context;
import com.jchirp.core.usecases.FollowUser;
import com.jchirp.core.usecases.ReadPost;
import com.jchirp.core.usecases.ShowWall;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.externals.CurrentTime;
import com.jchirp.externals.InMemoryGateway;

public class Main {

    public static void main(String[] args) {

        Action handler = buildIInfrastructure();

        while (true) {
            printPrompt();

            String input = System.console().readLine();

            if (doesProgramExit(input))
                break;

            System.out.print(handler.handleRequest(input));
        }
    }

    private static Action buildIInfrastructure() {
        Context.gateway = new InMemoryGateway();
        Context.timestamp = new CurrentTime();

        Action readAction = new ReadAction(new ReadPost());
        Action writeAction = new WriteAction(new WritePost());
        Action followAction = new FollowAction(new FollowUser());
        Action wallAction = new WallAction(new ShowWall());

        writeAction.setNext(followAction);
        followAction.setNext(wallAction);
        wallAction.setNext(readAction);

        return  writeAction;
    }

    private static void printPrompt() {
        System.out.print("> ");
    }

    private static boolean doesProgramExit(String input) {
        return input.toUpperCase().equals("EXIT");
    }
}
