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

    private User getUser() {
        return Context.gateway.getUser(USER_NAME);
    }

    private void setUser(String userName) {
        Context.gateway.setUser(new User(userName));
    }

    @Before
    public void setUp() throws Exception {
        followUserUsecase = new FollowUser();
        requestMsg = new RequestMsg(USER_NAME, FOLLOWED_USER_NAME);
        Context.gateway = new InMemoryGateway();
    }

    @Test
    public void whenUserDoesNotExistDoNothing(){
        followUserUsecase.execute(requestMsg);
        assertNull(getUser());
    }

    @Test
    public void whenFollowedUserDoesNotExistDoNothing(){
        String userName = USER_NAME;
        setUser(userName);
        followUserUsecase.execute(requestMsg);
        assertEquals(0, getUser().getFollowedUsers().size());
    }

    @Test
    public void follow_user(){
        setUser(USER_NAME);
        setUser(FOLLOWED_USER_NAME);
        followUserUsecase.execute(requestMsg);

        assertEquals(FOLLOWED_USER_NAME, getUser().getFollowedUsers().iterator().next());
    }

    @Test
    public void userCantFollowOtherUserMoreThanOneTime(){
        setUser(USER_NAME);
        setUser(FOLLOWED_USER_NAME);
        followUserUsecase.execute(requestMsg);
        followUserUsecase.execute(requestMsg);

        assertEquals(1, getUser().getFollowedUsers().size());
    }


    @Test
    public void userCantFollowHimself(){
        setUser(USER_NAME);
        followUserUsecase.execute(new RequestMsg(USER_NAME, USER_NAME));

        assertEquals(0, getUser().getFollowedUsers().size());
    }

    @Test
    public void whenUserInInputNotPresentDoNothing(){
        followUserUsecase.execute(new RequestMsg("", FOLLOWED_USER_NAME));
        assertNull(getUser());
    }

    @Test
    public void whenFollowedUserInInputNotPresentDoNothing(){
        followUserUsecase.execute(new RequestMsg(USER_NAME, ""));
        assertNull(Context.gateway.getUser(""));
    }

}