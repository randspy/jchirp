package com.jchirp.core.usecases;

import com.jchirp.core.entities.Post;
import com.jchirp.core.entities.User;
import com.jchirp.core.external.Context;
import com.jchirp.core.messages.PostMsg;
import com.jchirp.core.messages.RequestMsg;
import com.jchirp.core.messages.ResponseMsg;

public class ReadPost implements Command {
    @Override
    public ResponseMsg execute(RequestMsg requestMsg) {

        User user = Context.gateway.getUser(requestMsg.getUserName());
        ResponseMsg responseMsg = new ResponseMsg();
        if (user != null) {
            for (Post post : user.getPosts()) {
                responseMsg.addPost(buildPost(requestMsg, post));
            }
        }
        return responseMsg;
    }

    private PostMsg buildPost(RequestMsg requestMsg, Post post) {
        return new PostMsg.Builder()
                .withUserName(requestMsg.getUserName())
                .withContent(post.getContent())
                .withTimestamp(post.getTimestamp())
                .withCurrentTime(Context.timestamp.now())
                .build();
    }
}
