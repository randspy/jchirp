package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.ReadPost;
import com.jchirp.externals.InMemoryGateway;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadPostTest {

    private static String USER_NAME = "USER";

    private Command readPostUsecase;

    @Before
    public void setUp() throws Exception {
        readPostUsecase = new ReadPost();
        Context.gateway = new InMemoryGateway();
    }

    @Test public void
    whenUserNotPresentNoPostsReturned(){
        ResponseMsg response = readPostUsecase.execute(new RequestMsg(USER_NAME, ""));
        assertEquals(0, response.posts().size());
    }

    @Test public void
    readUsersPosts(){
        String content = "content";

        DateTime timestamp = new DateTime();
        Context.timestamp = new StabTimestamp(timestamp);

        User user = new User(USER_NAME);
        user.addPost(new Post(content, timestamp));
        Context.gateway.setUser(user);

        ResponseMsg responseMsg = readPostUsecase.execute(new RequestMsg(USER_NAME, ""));

        assertEquals(USER_NAME, responseMsg.posts().get(0).getUserName());
        assertEquals(content, responseMsg.posts().get(0).getContent());
        assertEquals(timestamp, responseMsg.posts().get(0).getPostTimestamp());
    }
}
