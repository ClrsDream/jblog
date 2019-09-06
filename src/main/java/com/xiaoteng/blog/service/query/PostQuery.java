package com.xiaoteng.blog.service.query;

import com.xiaoteng.blog.model.User;

import java.util.ArrayList;
import java.util.List;

public class PostQuery {
    private User user;

    private List<User> favUsers = new ArrayList<>();

    public List<User> getFavUsers() {
        return favUsers;
    }

    public void setFavUsers(List<User> favUsers) {
        this.favUsers = favUsers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostQuery{" +
                "user=" + user +
                '}';
    }
}
