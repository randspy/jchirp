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
    private ConsoleInputHandler followHandler;
    private SpyPost spyFollowPost;

    @Before
    public void setUp(){
        spyFollowPost = new SpyPost();
        followHandler = new FollowHandler(spyFollowPost);
    }

    @Test
    public void whenNotCorrectHandlerGoToNextHandler(){
        SpyHandler spyhandler = new SpyHandler();
        followHandler.setNext(spyhandler);
        String response = followHandler.handleRequest("");
        assertTrue(spyhandler.wasCalled());
        assertEquals(spyhandler.getSpyHandlerResponse(), response);
    }

    @Test
    public void  whenCorrectHandlerFollowUsecaseExecuted(){
        followHandler.handleRequest(USER + FOLLOWS + FOLLOWED_USER);
        assertEquals(USER, spyFollowPost.getRequestMsg().getUserName());
        assertEquals(FOLLOWED_USER, spyFollowPost.getRequestMsg().getContent());
    }

}