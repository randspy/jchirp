package com.jchirp.core.persistence;

import com.jchirp.core.entities.User;

public interface Gateway {
    User getUser(String user);

    void setUser(User user);
}
