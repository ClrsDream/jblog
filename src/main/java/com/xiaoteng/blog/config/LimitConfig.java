package com.xiaoteng.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "blog.limit")
public class LimitConfig {

    private int post;

    private int comment;

    private int registerComment;

    private int registerPost;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getRegisterComment() {
        return registerComment;
    }

    public void setRegisterComment(int registerComment) {
        this.registerComment = registerComment;
    }

    public int getRegisterPost() {
        return registerPost;
    }

    public void setRegisterPost(int registerPost) {
        this.registerPost = registerPost;
    }
}
