package com.jchirp.core.messages;

import java.util.ArrayList;
import java.util.List;

public class ResponseMsg {

    private List<PostMsg> postMsg = new ArrayList<PostMsg>();

    public List<PostMsg> posts() {
        return postMsg;
    }

    public void addPost(PostMsg postMsg) {
        this.postMsg.add(postMsg);
    }
}
