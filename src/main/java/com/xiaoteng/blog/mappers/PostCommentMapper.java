package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.PostComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCommentMapper {

    void insert(PostComment postComment);

    List<PostComment> selectPostComments(Long postId);

}
