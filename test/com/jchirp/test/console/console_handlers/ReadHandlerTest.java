package com.jchirp.test.console.console_handlers;

import com.jchirp.console.console_handlers.ConsoleInputHandler;
import com.jchirp.console.console_handlers.ReadHandler;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadHandlerTest {

    private static final String USER = "USER";
    private ConsoleInputHandler handler;
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
        handler = new ReadHandler(spyPost);
    }

    @Test public void
    whenEmptyInputReturnEmptyString(){
        assertEquals("", handler.handleRequest(""));
    }

    @Test public void
    whenProvidedUserDoesNotExistReturnEmptyString(){
        assertEquals("", handler.handleRequest(USER));
    }

    @Test public void
    whenProvidedExistsReturnItsPosts(){
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.addPost(buildPostMsg(USER, "Content", timestamp.minusHours(2)));
        responseMsg.addPost(buildPostMsg(USER, "Content two", timestamp.minusMinutes(1)));

        spyPost.setResponseMsg(responseMsg);
        assertEquals("Content two (1 minute)\nContent (2 hours)\n", handler.handleRequest(USER));
        assertEquals(USER, spyPost.getRequestMsg().getUserName());
    }

}