package com.jchirp.core.external;

import com.jchirp.core.entities.User;

public interface Gateway {
    User getUser(String user);

    void setUser(User user);
}
