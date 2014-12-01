package com.jchirp.test.console.console_handlers;

import com.jchirp.console.console_handlers.ConsoleInputHandler;
import com.jchirp.console.console_handlers.WriteHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WriteHandlerTest {

    private static final String ARROW = "->";
    private static final String USER = "USER";
    private static final String CONTENT = "CONTENT";
    private ConsoleInputHandler handler;
    private SpyPost spyWritePost;

    @Before
    public void setUp(){
        spyWritePost = new SpyPost();
        handler = new WriteHandler(spyWritePost);
    }

    @Test public void
    whenNotCorrectHandlerGoToNextHandler(){
        SpyHandler nextHandler = new SpyHandler();

        handler.setNext(nextHandler);
        String response = handler.handleRequest("");

        assertTrue(nextHandler.wasCalled());
        assertEquals(nextHandler.getSpyHandlerResponse(), response);
    }

    @Test public void
    whenCorrectHandlerWritePost(){
        handler.handleRequest(USER + ARROW + CONTENT);

        assertEquals(USER, spyWritePost.getRequestMsg().getUserName());
        assertEquals(CONTENT, spyWritePost.getRequestMsg().getContent());
    }
}
