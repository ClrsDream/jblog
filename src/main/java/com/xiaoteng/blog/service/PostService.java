package com.xiaoteng.blog.service;

import com.github.pagehelper.PageHelper;
import com.xiaoteng.blog.mappers.PostMapper;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.service.query.PostQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public List<Post> paginateOrderByPublishedAtDesc(int page, int pageSize, PostQuery query) {
        PageHelper.startPage(page, pageSize);
        return postMapper.paginate("published_at", "desc", null);
    }

    public List<Post> paginateOrderByReadNumDesc(int page, int pageSize, PostQuery query) {
        PageHelper.startPage(page, pageSize);
        return postMapper.paginate("read_num", "desc", null);
    }

    public Long count(PostQuery postQuery) {
        return postMapper.count(null);
    }

    public void createPost(Post post, String content, User user, String[] tagArr) {
        Post post1 = new Post();
        post1.setUserId(user.getId());
        post1.setTitle(post.getTitle());
        post1.setContent(content);
        post1.setPublishedAt(post.getPublishedAt());
        post1.setReadNum(0L);
        postMapper.insert(post);
    }

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public void readNumInc(Post post) {
        postMapper.readNumInc(post.getId());
    }

}
