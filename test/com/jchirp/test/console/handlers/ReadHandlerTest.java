package com.jchirp.test.console.handlers;

import com.jchirp.console.handlers.ConsoleInputHandler;
import com.jchirp.console.handlers.ReadHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadHandlerTest {

    public static final String USER = "USER";
    private ConsoleInputHandler readHandler;

    @Before
    public void setUp(){
        readHandler = new ReadHandler();
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

//        assertEquals("Content. (1 minute ago)", readHandler.handleRequest(USER));
    }

}