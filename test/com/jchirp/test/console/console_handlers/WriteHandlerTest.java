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
    private ConsoleInputHandler writeHandler;
    private SpyPost spyWritePost;

    @Before
    public void setUp(){
        spyWritePost = new SpyPost();
        writeHandler = new WriteHandler(spyWritePost);
    }

    @Test
    public void whenNotCorrectHandlerGoToNextHandler(){
        SpyHandler spyhandler = new SpyHandler();
        writeHandler.setNext(spyhandler);
        String response = writeHandler.handleRequest("");
        assertTrue(spyhandler.wasCalled());
        assertEquals(spyhandler.getSpyHandlerResponse(), response);
    }

    @Test
    public void  whenCorrectHandlerWriteUsecaseExecuted(){
        writeHandler.handleRequest(USER + ARROW + CONTENT);
        assertEquals(USER, spyWritePost.getRequestMsg().getUserName());
        assertEquals(CONTENT, spyWritePost.getRequestMsg().getContent());
    }
}
