package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.WriterHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WriteHandlerTest {

    public static final String ARROW = "->";
    private ConsoleInputHandler writeHandler;
    private SpyWritePost spyWritePost;
    private static final String USER = "USER";

    @Before
    public void setUp(){

        writeHandler = new WriterHandler();
        spyWritePost = new SpyWritePost();
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
        String content = "CONTENT";

        writeHandler.setUsecase(spyWritePost);
        writeHandler.handleRequest(USER + ARROW + content);

        assertEquals(USER, spyWritePost.getRequest().getUserName());
        assertEquals(content, spyWritePost.getRequest().getContent());
    }

    @Test
    public void WhenEmptyPostStillWritePost(){
        writeHandler.setUsecase(spyWritePost);
        writeHandler.handleRequest(USER + ARROW);
        assertEquals(USER, spyWritePost.getRequest().getUserName());
        assertEquals("", spyWritePost.getRequest().getContent());
    }
}
