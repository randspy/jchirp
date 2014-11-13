package com.jchirp.test.core.usecases;

import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.ReadPost;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadPostTest {

    private static String USER = "USER";

    private Command readPostUsecase;

    @Before
    public void setUp() throws Exception {
        readPostUsecase = new ReadPost();
    }

    @Test
    public void when_user_does_not_exist_empty_response(){
        ResponseMsg response = readPostUsecase.execute(new RequestMsg(USER, ""));
        assertEquals(0, response.posts().size());
    }

    @Test
    public void read_user_posts(){
//        ResponseMsg responseMsg = readPostUsecase.execute(new RequestMsg(USER, ""));
//        assertEquals(USER, responseMsg.posts().get(0).userName());
    }
}
