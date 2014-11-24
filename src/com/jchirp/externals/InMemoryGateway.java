package com.jchirp.externals;

import com.jchirp.core.entities.User;
import com.jchirp.core.external.Gateway;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGateway implements Gateway {
    private Map<String, User> allUsers;

    public InMemoryGateway() {
        allUsers = new HashMap<String, User>();
    }

    @Override
    public User getUser(String userName) {
        return allUsers.get(userName);
    }

    @Override
    public void setUser(User user) {
        allUsers.put(user.getUserName(), user);
    }
}
