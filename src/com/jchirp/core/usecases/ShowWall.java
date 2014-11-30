package com.jchirp.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

import java.util.Set;

public class ShowWall implements Command {
    private ResponseMsg responseMsg;

    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {

        responseMsg = new ResponseMsg();

        String userName = requestMsg.getUserName();
        addPostsForUserWithName(userName);
        addFollowedUsersPosts(userName);

        return responseMsg;
    }

    private void addFollowedUsersPosts(String userName) {
        User user = Context.gateway.getUser(userName);
        if(user != null) {
            Set<String> followedUsers = user.getFollowedUsers();
            for(String followedUserName : followedUsers) {
                addPostsForUserWithName(followedUserName);
            }
        }
    }

    private void addPostsForUserWithName(String userName) {
        User user = Context.gateway.getUser(userName);

        if (user != null) {
            for (Post post : user.getPosts()) {
                responseMsg.addPost(buildPostMsg(userName, post));
            }
        }
    }

    private PostMsg buildPostMsg(String userName, Post post) {
        return new PostMsg(userName, post.getContent(), post.getTimestamp());
    }
}
