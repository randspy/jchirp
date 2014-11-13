package com.jchirp.core.messages;

import java.util.LinkedList;
import java.util.List;

public class ResponseMsg {

    public List<PostMsg> posts() {
        return new LinkedList<PostMsg>();
    }
}
