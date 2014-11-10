package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.messages.Request;
import com.jchirp.core.persistence.Context;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.test.core.usecases.persistence.MockGateway;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WritePostTest {

    public static final String USER = "user";
    public static final String CONTENT = "content";

    @Test
    public void user_is_added_if_does_not_exist_already(){
        Command usecase = new WritePost();
        Context.gateway = new MockGateway();

        Request request = new Request(USER, CONTENT);

        usecase.execute(request);
        User user = Context.gateway.getUser(USER);
        assertEquals(USER, user.getUserName());
    }
}