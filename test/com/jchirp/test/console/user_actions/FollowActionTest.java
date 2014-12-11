package com.jchirp.test.console.user_actions;

import com.jchirp.console.user_actions.Action;
import com.jchirp.console.user_actions.FollowAction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FollowActionTest {

    private static final String USER = "user";
    private static final String FOLLOWS = "follows";
    private static final String FOLLOWED_USER = "followed user";
    private Action action;
    private SpyPost spyFollowPost;

    @Before
    public void setUp(){
        spyFollowPost = new SpyPost();
        action = new FollowAction(spyFollowPost);
    }

    @Test public void
    whenNotCorrectHandlerGoToNextHandler(){
        SpyAction nextAction = new SpyAction();

        action.setNext(nextAction);
        assertEquals(nextAction.getSpyHandlerResponse(), action.handleRequest(""));
        assertTrue(nextAction.wasCalled());
    }

    @Test public void
    whenCorrectHandlerAddFollowedUser(){
        action.handleRequest(USER + FOLLOWS + FOLLOWED_USER);

        assertEquals(USER, spyFollowPost.getRequestMsg().getUserName());
        assertEquals(FOLLOWED_USER, spyFollowPost.getRequestMsg().getContent());
    }

}