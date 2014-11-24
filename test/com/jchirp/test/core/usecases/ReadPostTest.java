package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.ReadPost;
import com.jchirp.externals.InMemoryGateway;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadPostTest {

    private static String USER = "USER";

    private Command readPostUsecase;

    @Before
    public void setUp() throws Exception {
        readPostUsecase = new ReadPost();
        Context.gateway = new InMemoryGateway();
    }

    @Test
    public void when_user_does_not_exist_empty_response(){
        ResponseMsg response = readPostUsecase.execute(new RequestMsg(USER, ""));
        assertEquals(0, response.posts().size());
    }

    @Test
    public void read_user_posts(){
        User user = new User(USER);
        DateTime timestamp = new DateTime();
        String content = "content";
        user.addPost(new Post(content, timestamp));
        Context.gateway.setUser(user);

        ResponseMsg responseMsg = readPostUsecase.execute(new RequestMsg(USER, ""));
        assertEquals(USER, responseMsg.posts().get(0).getUserName());
        assertEquals(content, responseMsg.posts().get(0).getContent());
        assertEquals(timestamp, responseMsg.posts().get(0).getTimestamp());
    }
}
