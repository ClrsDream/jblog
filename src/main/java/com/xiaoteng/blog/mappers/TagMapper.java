package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    void insert(Tag tag);

    Tag findByName(String name);
}
