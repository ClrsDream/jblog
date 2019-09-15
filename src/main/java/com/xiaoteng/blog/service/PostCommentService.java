package com.xiaoteng.blog.service;

import com.xiaoteng.blog.mappers.PostCommentMapper;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.PostComment;
import com.xiaoteng.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {

    @Autowired
    private PostCommentMapper postCommentMapper;

    public PostComment create(String content, Long commentId, User user, Post post) {
        PostComment postComment = new PostComment();
        postComment.setUserId(user.getId());
        postComment.setPostId(post.getId());
        postComment.setContent(content);
        postComment.setCommentId(commentId);
        postCommentMapper.insert(postComment);
        return postComment;
    }

    public List<PostComment> selectPostComments(Post post) {
        return postCommentMapper.selectPostComments(post.getId());
    }

}
