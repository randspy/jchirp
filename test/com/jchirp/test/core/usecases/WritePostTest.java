package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.messages.Request;
import com.jchirp.core.external.Context;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.test.core.external.StabTimestamp;
import com.jchirp.test.core.external.StabGateway;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WritePostTest {

    private static final String USER = "user";
    private static final String POST = "post";
    private Command usecase;
    private DateTime now;

    private void executeRequest(String user, String content) {
        usecase.execute(new Request(user, content));
    }

    private void assertUser(String userName, String content, int postIndex) {
        User user = Context.gateway.getUser(userName);

        assertEquals(userName, user.getUserName());
        assertEquals(content, user.getPosts().get(postIndex).getContent());
        assertEquals(now, user.getPosts().get(postIndex).getTimestamp());
    }

    @Before
    public void setUp(){
        usecase = new WritePost();
        now = new DateTime();
        Context.gateway = new StabGateway();
        Context.timestamp = new StabTimestamp(now);
    }

    @Test
    public void user_is_added_if_does_not_exist_already(){

        executeRequest(USER, POST);

        int postIndex = 0;
        assertUser(USER, POST, postIndex);
    }

    @Test
    public void new_post_is_added_to_user(){
        String newPost = "new post";

        executeRequest(USER, POST);
        executeRequest(USER, newPost);

        int newPostIndex = 1;
        assertUser(USER, newPost, newPostIndex);
    }
}