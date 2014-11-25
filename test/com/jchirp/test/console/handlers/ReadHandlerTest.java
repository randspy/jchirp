package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.ReadHandler;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadHandlerTest {

    public static final String USER = "USER";
    private ConsoleInputHandler readHandler;
    private SpyPost spyPost;
    private DateTime timestamp = new DateTime();

    @Before
    public void setUp(){
        Context.timestamp = new StabTimestamp(timestamp);
        spyPost = new SpyPost();
        readHandler = new ReadHandler(spyPost);
    }

    @Test
    public void whenEmptyInputReturnEmptyString(){
        assertEquals("", readHandler.handleRequest(""));
    }

    @Test
    public void whenProvidedUserDoesNotExistReturnEmptyString(){
        assertEquals("", readHandler.handleRequest(USER));
    }

    @Test
    public void  whenProvidedExistsReturnItsPosts(){
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.addPost(new PostMsg(USER, "Content", timestamp.minusMinutes(2)));
        responseMsg.addPost(new PostMsg(USER, "Content two", timestamp.minusHours(1)));

        spyPost.setResponseMsg(responseMsg);
        assertEquals("Content (2 minutes)\nContent two (1 hour)\n", readHandler.handleRequest(USER));
        assertEquals(USER, spyPost.getRequestMsg().getUserName());
    }

}