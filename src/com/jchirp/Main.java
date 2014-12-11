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

        UserAction handler = buildIInfrastructure();

        while (true) {
            printPrompt();

            String input = System.console().readLine();

            if (doesProgramExit(input))
                break;

            System.out.print(handler.handleRequest(input));
        }
    }

    private static UserAction buildIInfrastructure() {
        Context.gateway = new InMemoryGateway();
        Context.timestamp = new CurrentTime();

        UserAction readAction = new ReadAction(new ReadPost());
        UserAction writeAction = new WriteAction(new WritePost());
        UserAction followAction = new FollowAction(new FollowUser());
        UserAction wallAction = new WallAction(new ShowWall());

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
