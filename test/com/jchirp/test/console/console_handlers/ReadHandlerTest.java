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
        responseMsg.addPost(new PostMsg(USER, "Content", timestamp.minusHours(2)));
        responseMsg.addPost(new PostMsg(USER, "Content two", timestamp.minusMinutes(1)));

        spyPost.setResponseMsg(responseMsg);
        assertEquals("Content two (1 minute)\nContent (2 hours)\n", readHandler.handleRequest(USER));
        assertEquals(USER, spyPost.getRequestMsg().getUserName());
    }

}