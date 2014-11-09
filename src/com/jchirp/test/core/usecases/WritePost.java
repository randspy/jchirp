package com.jchirp.test.core.usecases;

import com.jchirp.core.entities.User;
import com.jchirp.core.messages.Request;
import com.jchirp.core.persistence.Context;

public class WritePost implements Command {
    public void execute(Request request) {
        User user = Context.gateway.getUser(request.getUserName());
        if (user == null)
            user = new User();

        user.setUserName(request.getUserName());
        Context.gateway.setUser(user);
    }
}
