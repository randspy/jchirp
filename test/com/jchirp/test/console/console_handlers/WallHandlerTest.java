package com.jchirp.test.console.console_handlers;

import com.jchirp.console.console_handlers.ConsoleInputHandler;
import com.jchirp.console.console_handlers.WallHandler;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WallHandlerTest {

    private static final String USER_NAME = "user";
    private static final String WALL = "wall";

    private ConsoleInputHandler handler;
    private SpyPost spyPost;
    private DateTime timestamp = new DateTime();

    @Before
    public void setUp(){
        Context.timestamp = new StabTimestamp(timestamp);
        spyPost = new SpyPost();
        handler = new WallHandler(spyPost);
    }

    @Test
    public void whenNotCorrectHandlerGoToNextHandler(){
        SpyHandler spyhandler = new SpyHandler();
        handler.setNext(spyhandler);
        String response = handler.handleRequest("");
        assertTrue(spyhandler.wasCalled());
        assertEquals(spyhandler.getSpyHandlerResponse(), response);
    }

    @Test
    public void whenCorrectHandlerWallUsecaseExecuted(){

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.addPost(new PostMsg(USER_NAME, "Content one", timestamp.minusHours(2)));
        responseMsg.addPost(new PostMsg("fallowed user", "Content of followed user", timestamp.minusMinutes(1)));
        responseMsg.addPost(new PostMsg(USER_NAME, "Content two", timestamp.minusHours(1)));

        spyPost.setResponseMsg(responseMsg);
        String expectedResponse = "fallowed user" + " - Content of followed user (1 minute)\n";
        expectedResponse += USER_NAME + " - Content two (1 hour)\n";
        expectedResponse += USER_NAME + " - Content one (2 hours)\n";
        assertEquals(expectedResponse, handler.handleRequest(USER_NAME + WALL));
        assertEquals(USER_NAME, spyPost.getRequestMsg().getUserName());
    }
}