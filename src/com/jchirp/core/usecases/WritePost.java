package com.jchirp.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.ResponseMsg;
import org.joda.time.DateTime;

public class WritePost implements Command {
    public ResponseMsg execute(RequestMsg requestMsg) {
        User user = Context.gateway.getUser(requestMsg.getUserName());
        if (user == null)
            user = new User(requestMsg.getUserName());

        DateTime timestamp = Context.timestamp.now();
        Post post = new Post(requestMsg.getContent(), timestamp);
        user.addPost(post);
        Context.gateway.setUser(user);

        return new ResponseMsg();
    }
}
