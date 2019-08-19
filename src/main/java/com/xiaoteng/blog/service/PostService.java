package com.xiaoteng.blog.service;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Value("${blog.index.page-size}")
    private int pageSize;

    public Page<Post> getPostPaginate(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "publishedAt"));
        return postRepository.findAll(pageable);
    }

}
