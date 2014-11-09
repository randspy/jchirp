package com.jchirp.test.core.usecases.persistence;

import com.jchirp.core.entities.User;
import com.jchirp.core.persistence.Gateway;

import java.util.HashMap;
import java.util.Map;

public class MockGateway implements Gateway {
    private Map<String, User> allUsers;

    public MockGateway() {
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
