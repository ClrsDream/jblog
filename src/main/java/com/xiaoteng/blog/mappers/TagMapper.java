package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    void insert(Tag tag);

    Tag findByName(String name);

    List<Tag> all();

    List<Tag> selectTagByPostId(Long postId);
}
