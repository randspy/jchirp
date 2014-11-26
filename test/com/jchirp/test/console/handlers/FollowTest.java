package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.FollowHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FollowTest {

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
        followHandler.handleRequest("");
        assertTrue(spyhandler.wasCalled());
    }

    @Test
    public void  whenCorrectHandlerWriteUsecaseExecuted(){
        followHandler.handleRequest(USER + FOLLOWS + FOLLOWED_USER);
        assertEquals(USER, spyFollowPost.getRequestMsg().getUserName());
        assertEquals(FOLLOWED_USER, spyFollowPost.getRequestMsg().getContent());
    }

    @Test
    public void whenFollowedUserIsEmptyDoNothing(){
        assertEquals("", followHandler.handleRequest(USER + FOLLOWS));
        assertEquals(null, spyFollowPost.getRequestMsg());
    }

    @Test
    public void whenNoFollowsKeywordDoNothing(){
        assertEquals("", followHandler.handleRequest(FOLLOWS + FOLLOWED_USER));
        assertEquals(null, spyFollowPost.getRequestMsg());
    }

}