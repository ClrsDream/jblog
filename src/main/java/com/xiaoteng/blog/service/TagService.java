package com.xiaoteng.blog.service;

import com.xiaoteng.blog.mappers.TagMapper;
import com.xiaoteng.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<Tag> findAll() {
        return tagMapper.all();
    }

    public Tag findById(Long id) {
        return tagMapper.findById(id);
    }

    public Tag findTag(String name) {
        return tagMapper.findByName(name);
    }

    public Tag create(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagMapper.insert(tag);
        return tag;
    }

    public List<Tag> selectTags(Long postId) {
        return tagMapper.selectTagByPostId(postId);
    }

}
