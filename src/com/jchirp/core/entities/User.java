package com.jchirp.core.entities;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String userName;
    private List<Post> posts = new LinkedList<Post>();

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
