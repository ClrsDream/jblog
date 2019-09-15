package com.xiaoteng.blog.service;

import com.github.pagehelper.PageHelper;
import com.xiaoteng.blog.mappers.PostMapper;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.service.query.PostQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final static Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagService tagService;

    public List<Post> paginateOrderByPublishedAtDesc(int page, int pageSize, PostQuery query) {
        PageHelper.startPage(page, pageSize);
        Long userId = null;
        if (query != null) {
            if (query.getUser() != null) {
                userId = query.getUser().getId();
            }
        }
        return postMapper.paginate("published_at", "desc", userId);
    }

    public List<Post> paginateOrderByReadNumDesc(int page, int pageSize, PostQuery query) {
        PageHelper.startPage(page, pageSize);
        Long userId = null;
        if (query != null) {
            if (query.getUser() != null) {
                userId = query.getUser().getId();
            }
        }
        return postMapper.paginate("read_num", "desc", userId);
    }

    public Long count(PostQuery query) {
        Long userId = null;
        if (query != null) {
            if (query.getUser() != null) {
                userId = query.getUser().getId();
            }
        }
        log.info("userId:{}", userId);
        return postMapper.count(userId);
    }

    public void createPost(Post post, String content, User user, String[] tagArr) {
        Post post1 = new Post();
        post1.setUserId(user.getId());
        post1.setTitle(post.getTitle());
        post1.setContent(content);
        post1.setPublishedAt(post.getPublishedAt());
        post1.setReadNum(0L);
        Long postId = postMapper.insert(post);
        log.info("postId:{}", postId);

        // 同步标签
        for (String tagName : tagArr) {
            // 检查标签是否存在
            Tag tag = tagService.findTag(tagName);
            if (tag == null) {
                // 创建标签
                tag = tagService.create(tagName);
            }
            // 关联
            postMapper.tagRelationCreate(postId, tag.getId());
        }
    }

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public void readNumInc(Post post) {
        postMapper.readNumInc(post.getId());
    }

    public Long userFavCount(User user) {
        return postMapper.userFavCount(user.getId());
    }

    public List<Post> paginateUserFav(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        return postMapper.paginateUserFav(user.getId());
    }

    public void userFavCountDec(Long postId) {
        postMapper.userFavCountDec(postId);
    }

    public void userFavCountInc(Long postId) {
        postMapper.userFavCountInc(postId);
    }

}
