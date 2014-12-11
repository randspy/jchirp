package com.jchirp.test.console.user_actions;

import com.jchirp.console.user_actions.UserAction;
import com.jchirp.console.user_actions.WriteAction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WriteActionTest {

    private static final String ARROW = "->";
    private static final String USER = "USER";
    private static final String CONTENT = "CONTENT";
    private UserAction action;
    private SpyPost spyWritePost;

    @Before
    public void setUp(){
        spyWritePost = new SpyPost();
        action = new WriteAction(spyWritePost);
    }

    @Test public void
    whenNotCorrectHandlerGoToNextHandler(){
        SpyAction nextAction = new SpyAction();

        action.setNext(nextAction);
        String response = action.handleRequest("");

        assertTrue(nextAction.wasCalled());
        assertEquals(nextAction.getSpyHandlerResponse(), response);
    }

    @Test public void
    whenCorrectHandlerWritePost(){
        action.handleRequest(USER + ARROW + CONTENT);

        assertEquals(USER, spyWritePost.getRequestMsg().getUserName());
        assertEquals(CONTENT, spyWritePost.getRequestMsg().getContent());
    }
}
