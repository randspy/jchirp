package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.WriteHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WriteHandlerTest {

    public static final String ARROW = "->";
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
        writeHandler.handleRequest("");
        assertTrue(spyhandler.wasCalled());
    }

    @Test
    public void  whenCorrectHandlerWriteUsecaseExecuted(){
        writeHandler.handleRequest(USER + ARROW + CONTENT);
        assertEquals(USER, spyWritePost.getRequestMsg().getUserName());
        assertEquals(CONTENT, spyWritePost.getRequestMsg().getContent());
    }

    @Test
    public void whenEmptyPostContentNoPostWritten(){
        assertEquals("", writeHandler.handleRequest(USER + ARROW));
        assertEquals(null, spyWritePost.getRequestMsg());
    }

    @Test
    public void whenEmptyPostUserNameNoPostWritten(){
        assertEquals("", writeHandler.handleRequest(ARROW + CONTENT));
        assertEquals(null, spyWritePost.getRequestMsg());
    }

}
