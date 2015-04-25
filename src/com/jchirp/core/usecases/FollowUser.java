package com.jchirp.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

public class FollowUser implements Command {
    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {

        User user = Context.gateway.getUser(requestMsg.getUserName());
        User followed_user = Context.gateway.getUser(requestMsg.getContent());
        if (canFollowOtherUser(user, followed_user)) {
            user.addFollowedUsers(requestMsg.getContent());
        }

        return null;
    }

    private boolean canFollowOtherUser(User user, User followed_user) {
        return user != null && followed_user != null && !user.equals(followed_user);
    }
}
