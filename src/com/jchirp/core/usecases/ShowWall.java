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

        addUserPosts(requestMsg.getUserName());
        addFollowedUsersPosts(requestMsg.getUserName());

        return responseMsg;
    }

    private void addFollowedUsersPosts(String userName) {
        User user = Context.gateway.getUser(userName);
        if(user != null) {
            Set<String> followedUsers = user.getFollowedUsers();
            for(String name : followedUsers) {
                addUserPosts(name);
            }
        }
    }

    private void addUserPosts(String userName) {
        User user = Context.gateway.getUser(userName);

        if (user != null) {
            for (Post post : user.getPosts()) {
                responseMsg.addPost(buildPost(userName, post));
            }
        }
    }

    private PostMsg buildPost(String userName, Post post) {
        return new PostMsg.Builder()
                .withUserName(userName)
                .withContent(post.getContent())
                .withTimestamp(post.getTimestamp())
                .withCurrentTime(Context.timestamp.now())
                .build();
    }

}
