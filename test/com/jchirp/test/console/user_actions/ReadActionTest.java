package com.jchirp.test.console.user_actions;

import com.jchirp.console.user_actions.UserAction;
import com.jchirp.console.user_actions.ReadAction;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadActionTest {

    private static final String USER = "USER";
    private UserAction action;
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
        action = new ReadAction(spyPost);
    }

    @Test public void
    whenEmptyInputReturnEmptyString(){
        assertEquals("", action.handleRequest(""));
    }

    @Test public void
    whenProvidedUserDoesNotExistReturnEmptyString(){
        assertEquals("", action.handleRequest(USER));
    }

    @Test public void
    whenProvidedExistsReturnItsPosts(){
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.addPost(buildPostMsg(USER, "Content", timestamp.minusHours(2)));
        responseMsg.addPost(buildPostMsg(USER, "Content two", timestamp.minusMinutes(1)));

        spyPost.setResponseMsg(responseMsg);
        assertEquals("Content two (1 minute)\nContent (2 hours)\n", action.handleRequest(USER));
        assertEquals(USER, spyPost.getRequestMsg().getUserName());
    }

}