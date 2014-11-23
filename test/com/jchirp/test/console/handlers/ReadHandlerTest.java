package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.ReadHandler;
import com.jchirp.console.time.CurrentTime;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.ResponseMsg;
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
        spyPost = new SpyPost();
        readHandler = new ReadHandler(spyPost, new CurrentTime(){
            @Override
            public DateTime now(){
                return timestamp;
            }
        });
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
        responseMsg.addPost(new PostMsg(USER, "Content", timestamp));

        spyPost.setResponseMsg(responseMsg);
        assertEquals("Content (0 seconds)", readHandler.handleRequest(USER));
        assertEquals(USER, spyPost.getRequestMsg().getUserName());
    }

}