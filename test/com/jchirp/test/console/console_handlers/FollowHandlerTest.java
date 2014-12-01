package com.jchirp.test.console.console_handlers;

import com.jchirp.console.console_handlers.ConsoleInputHandler;
import com.jchirp.console.console_handlers.FollowHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FollowHandlerTest {

    private static final String USER = "user";
    private static final String FOLLOWS = "follows";
    private static final String FOLLOWED_USER = "followed user";
    private ConsoleInputHandler handler;
    private SpyPost spyFollowPost;

    @Before
    public void setUp(){
        spyFollowPost = new SpyPost();
        handler = new FollowHandler(spyFollowPost);
    }

    @Test public void
    whenNotCorrectHandlerGoToNextHandler(){
        SpyHandler nextHandler = new SpyHandler();

        handler.setNext(nextHandler);
        assertEquals(nextHandler.getSpyHandlerResponse(), handler.handleRequest(""));
        assertTrue(nextHandler.wasCalled());
    }

    @Test public void
    whenCorrectHandlerAddFollowedUser(){
        handler.handleRequest(USER + FOLLOWS + FOLLOWED_USER);

        assertEquals(USER, spyFollowPost.getRequestMsg().getUserName());
        assertEquals(FOLLOWED_USER, spyFollowPost.getRequestMsg().getContent());
    }

}