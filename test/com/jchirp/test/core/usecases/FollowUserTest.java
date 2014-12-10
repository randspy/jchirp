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

    private static final String USER_NAME = "user";
    private static final String FOLLOWED_USER_NAME = "followed user";
    private Command followUserUsecase;
    private RequestMsg requestMsg;

    private User getUserFromGateway() {
        return Context.gateway.getUser(USER_NAME);
    }

    private void setUserInGateway(String userName) {
        Context.gateway.setUser(new User(userName));
    }

    @Before
    public void setUp() throws Exception {
        followUserUsecase = new FollowUser();
        requestMsg = new RequestMsg(USER_NAME, FOLLOWED_USER_NAME);
        Context.gateway = new InMemoryGateway();
    }

    @Test public void
    whenUserDoesNotExistDoNothing(){
        followUserUsecase.execute(requestMsg);
        assertNull(getUserFromGateway());
    }

    @Test public void
    whenFollowedUserDoesNotExistDoNothing(){
        setUserInGateway(USER_NAME);

        followUserUsecase.execute(requestMsg);

        assertEquals(0, getUserFromGateway().getFollowedUsers().size());
    }

    @Test public void
    followUser(){
        setUserInGateway(USER_NAME);
        setUserInGateway(FOLLOWED_USER_NAME);

        followUserUsecase.execute(requestMsg);

        assertEquals(FOLLOWED_USER_NAME, getUserFromGateway().getFollowedUsers().iterator().next());
    }

    @Test public void
    followMoreThanOneUserUser(){
        String secondFollowedUser = "second followed user";
        setUserInGateway(USER_NAME);
        setUserInGateway(FOLLOWED_USER_NAME);
        setUserInGateway(secondFollowedUser);

        followUserUsecase.execute(requestMsg);
        followUserUsecase.execute(new RequestMsg(USER_NAME, secondFollowedUser));

        assertEquals(2, getUserFromGateway().getFollowedUsers().size());
    }

    @Test public void
    userCantFollowOtherUserMoreThanOneTime(){
        setUserInGateway(USER_NAME);
        setUserInGateway(FOLLOWED_USER_NAME);
        
        followUserUsecase.execute(requestMsg);
        followUserUsecase.execute(requestMsg);

        assertEquals(1, getUserFromGateway().getFollowedUsers().size());
    }

    @Test public void
    userCantFollowHimself(){
        setUserInGateway(USER_NAME);
        
        followUserUsecase.execute(new RequestMsg(USER_NAME, USER_NAME));

        assertEquals(0, getUserFromGateway().getFollowedUsers().size());
    }
}