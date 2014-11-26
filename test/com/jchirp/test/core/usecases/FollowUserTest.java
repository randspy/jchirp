package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.FollowUser;
import com.jchirp.externals.InMemoryGateway;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FollowUserTest {

    public static final String USER_NAME = "user";
    public static final String FOLLOWED_USER_NAME = "followed user";
    private Command followUserUsecase;
    private RequestMsg requestMsg;

    @Before
    public void setUp() throws Exception {
        followUserUsecase = new FollowUser();
        requestMsg = new RequestMsg(USER_NAME, FOLLOWED_USER_NAME);
        Context.gateway = new InMemoryGateway();
    }

    @Test
    public void whenUserDoesNotExistDoNothing(){
        followUserUsecase.execute(requestMsg);
        assertNull(Context.gateway.getUser(USER_NAME));
    }

    @Test
    public void whenFollowedUserDoesNotExistDoNothing(){
        Context.gateway.setUser(new User(USER_NAME));
        followUserUsecase.execute(requestMsg);
        assertEquals(0, Context.gateway.getUser(USER_NAME).getFollowedUsers().size());
    }

    @Test
    public void follow_user(){
        Context.gateway.setUser(new User(USER_NAME));
        Context.gateway.setUser(new User(FOLLOWED_USER_NAME));
        followUserUsecase.execute(requestMsg);

        assertEquals(FOLLOWED_USER_NAME, Context.gateway.getUser(USER_NAME).getFollowedUsers().get(0));
    }

}