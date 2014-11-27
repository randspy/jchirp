package com.jchirp.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

public class WritePost implements Command {
    public ResponseMsg execute(RequestMsg requestMsg) {
        if (isInputValid(requestMsg)) {

            Post post = new Post(requestMsg.getContent(), Context.timestamp.now());

            User user = getUser(requestMsg);
            user.addPost(post);
            Context.gateway.setUser(user);
        }
        return null;
    }

    private User getUser(RequestMsg requestMsg) {
        User user = Context.gateway.getUser(requestMsg.getUserName());
        if (user == null)
            user = new User(requestMsg.getUserName());
        return user;
    }

    private boolean isInputValid(RequestMsg requestMsg) {
        return !requestMsg.getUserName().isEmpty() && !requestMsg.getContent().isEmpty();
    }
}
