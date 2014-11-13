package com.jchirp.test.core.external;

import com.jchirp.core.entities.User;
import com.jchirp.core.external.Gateway;

import java.util.HashMap;
import java.util.Map;

public class StabGateway implements Gateway {
    private Map<String, User> allUsers;

    public StabGateway() {
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
