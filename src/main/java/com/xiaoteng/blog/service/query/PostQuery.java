package com.xiaoteng.blog.service.query;

import com.xiaoteng.blog.model.User;

public class PostQuery {
    private User user;

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
