package com.jchirp.core.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class User {
    private String userName;
    private List<Post> posts = new LinkedList<Post>();
    private Set<String> followedUsers = new HashSet<String>();

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

    public Set<String> getFollowedUsers() {
        return followedUsers;
    }

    public void addFollowedUsers(String followedUser) {
        followedUsers.add(followedUser);
    }
}
