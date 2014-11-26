package com.jchirp.core.entities;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String userName;
    private List<Post> posts = new LinkedList<Post>();
    private List<String> followedUsers = new LinkedList<String>();

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

    public List<String> getFollowedUsers() {
        return followedUsers;
    }

    public void addFollowedUsers(String followedUser) {
        followedUsers.add(followedUser);
    }
}
