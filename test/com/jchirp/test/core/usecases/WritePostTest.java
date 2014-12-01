package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.WritePost;
import com.jchirp.externals.InMemoryGateway;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WritePostTest {

    private static final String USER_NAME = "user";
    private static final String POST = "content";
    private Command writePostUsecase;
    private DateTime now;

    private void executeRequest(String user, String content) {
        writePostUsecase.execute(new RequestMsg(user, content));
    }

    private void assertUser(String userName, String content, int postIndex) {
        User user = Context.gateway.getUser(userName);

        assertEquals(userName, user.getUserName());
        assertEquals(content, user.getPosts().get(postIndex).getContent());
        assertEquals(now, user.getPosts().get(postIndex).getTimestamp());
    }

    @Before
    public void setUp(){
        writePostUsecase = new WritePost();
        now = new DateTime();
        Context.gateway = new InMemoryGateway();
        Context.timestamp = new StabTimestamp(now);
    }

    @Test public void
    userIsAddedIfDoesNotExistAlready(){

        executeRequest(USER_NAME, POST);

        assertUser(USER_NAME, POST, 0);
    }

    @Test public void
    newPostIsAddedToUser(){
        String newPost = "new content";

        executeRequest(USER_NAME, POST);
        executeRequest(USER_NAME, newPost);

        assertUser(USER_NAME, newPost, 1);
    }

    @Test public void
    whenUserFromInputIsEmptyDoNoting(){
        executeRequest("", POST);
        assertNull(Context.gateway.getUser(""));
    }

    @Test public void
    whenContentFromInputIsEmptyDoNoting(){
        executeRequest(USER_NAME, "");
        assertNull(Context.gateway.getUser(USER_NAME));
    }
}