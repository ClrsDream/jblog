package com.xiaoteng.blog.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserPostFav implements Serializable {

    private Long userId;

    private Long postId;

    public UserPostFav() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
