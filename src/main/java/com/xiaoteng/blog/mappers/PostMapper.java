package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    void insert(Post post);

    Post findById(Long id);

    void readNumInc(Long id);
}
