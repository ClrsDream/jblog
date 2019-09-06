package com.xiaoteng.blog.service;

import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findTagByName(String name) {
        Optional<Tag> optionalTag = tagRepository.findTagByName(name);
        return optionalTag.orElse(null);
    }

    public Tag createTagFromName(String tagName) {
        Tag tag = findTagByName(tagName);
        if (null == tag) {
            // 创建tag
            tag = create(tagName);
        }
        return tag;
    }

    public Tag create(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
        return tag;
    }

}
