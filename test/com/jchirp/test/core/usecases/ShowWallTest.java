package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;
import com.jchirp.core.usecases.Command;
import com.jchirp.core.usecases.ShowWall;
import com.jchirp.externals.InMemoryGateway;
import com.jchirp.test.external.StabTimestamp;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShowWallTest {

    private static final String USER_NAME = "user name";
    private static final String TEXT_ONE = "text one";
    private static final String TEXT_TWO = "text two";
    private Command usecase;
    private DateTime timestamp;

    private void addUserToGateway(User user, String content) {
        user.addPost(new Post(content, timestamp));
        Context.gateway.setUser(user);
    }

    private void assertUsacaseResponse(String userName, String content, int postNumberInCollection) {
        ResponseMsg responseMsg = usecase.execute(new RequestMsg(USER_NAME, ""));
        PostMsg postMsg = responseMsg.posts().get(postNumberInCollection);

        assertEquals(userName, postMsg.getUserName());
        assertEquals(content, postMsg.getContent());
        assertEquals(timestamp, postMsg.getPostTimestamp());
    }

    @Before
    public void setUp(){
        usecase = new ShowWall();
        timestamp = new DateTime();
        Context.gateway = new InMemoryGateway();
        Context.timestamp = new StabTimestamp(timestamp);
    }

    @Test
    public void
    whenUserDoesNotExistNoPostsReturned(){
        ResponseMsg responseMsg = usecase.execute(new RequestMsg(USER_NAME, ""));
        assertEquals(0, responseMsg.posts().size());
    }

    @Test public void
    whenUserIsNotFollowingAnyoneReturnOnlyUserPosts(){
        User user = new User(USER_NAME);
        addUserToGateway(user, TEXT_ONE);
        addUserToGateway(user, TEXT_TWO);
        assertUsacaseResponse(USER_NAME, TEXT_ONE, 0);
        assertUsacaseResponse(USER_NAME, TEXT_TWO, 1);
    }

    @Test public void
    whenUserContainsFollowingUsersReturnAlsoTheirPosts(){
        String followedUserName = "followed user";
        String followedUserContent = "followed user text";

        User followedUser = new User(followedUserName);
        addUserToGateway(followedUser, followedUserContent);

        User user = new User(USER_NAME);
        user.addFollowedUsers(followedUserName);
        addUserToGateway(user,TEXT_ONE);

        assertUsacaseResponse(USER_NAME, TEXT_ONE, 0);
        assertUsacaseResponse(followedUserName, followedUserContent, 1);
    }
}