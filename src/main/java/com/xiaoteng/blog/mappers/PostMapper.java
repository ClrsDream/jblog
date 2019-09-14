package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insert(Post post);

    Post findById(Long id);

    void readNumInc(Long id);

    List<Post> paginate(String sort, String direct, Long userId);

    Long count(Long userId);
}
