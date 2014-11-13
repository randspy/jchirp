package com.jchirp.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.messages.Request;
import com.jchirp.core.external.Context;
import org.joda.time.DateTime;

public class WritePost implements Command {
    public void execute(Request request) {
        User user = Context.gateway.getUser(request.getUserName());
        if (user == null)
            user = new User(request.getUserName());

        DateTime timestamp = Context.timestamp.now();
        Post post = new Post(request.getContent(), timestamp);
        user.addPost(post);
        Context.gateway.setUser(user);
    }
}
