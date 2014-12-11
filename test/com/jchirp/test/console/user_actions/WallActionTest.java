package com.jchirp.test.console.user_actions;

import com.jchirp.console.user_actions.Action;
import com.jchirp.console.user_actions.WallAction;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WallActionTest {

    private static final String USER_NAME = "user";
    private static final String WALL = "wall";

    private Action action;
    private SpyPost spyPost;
    private DateTime timestamp = new DateTime();

    PostMsg buildPostMsg(String userName, String content, DateTime postTimestamp)
    {
        return new PostMsg.Builder()
                        .withUserName(userName)
                        .withContent(content)
                        .withTimestamp(postTimestamp)
                        .withCurrentTime(timestamp)
                        .build();
    }

    @Before
    public void setUp(){
        Context.timestamp = new StabTimestamp(timestamp);
        spyPost = new SpyPost();
        action = new WallAction(spyPost);
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
    whenCorrectHandlerShowUserWall(){

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.addPost(buildPostMsg(USER_NAME, "Content one", timestamp.minusHours(2)));
        responseMsg.addPost(buildPostMsg("fallowed user", "Content of followed user", timestamp.minusMinutes(1)));
        responseMsg.addPost(buildPostMsg(USER_NAME, "Content two", timestamp.minusHours(1)));

        spyPost.setResponseMsg(responseMsg);
        String expectedResponse = "fallowed user" + " - Content of followed user (1 minute)\n";
        expectedResponse += USER_NAME + " - Content two (1 hour)\n";
        expectedResponse += USER_NAME + " - Content one (2 hours)\n";

        assertEquals(expectedResponse, action.handleRequest(USER_NAME + WALL));
        assertEquals(USER_NAME, spyPost.getRequestMsg().getUserName());
    }
}