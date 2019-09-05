package com.xiaoteng.blog.service;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.PostRepository;
import com.xiaoteng.blog.service.query.PostPaginateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> paginateOrderByPublishedAtDesc(int page, int pageSize, PostPaginateQuery query) {
        return paginate(page, pageSize, "publishedAt", Sort.Direction.DESC, query);
    }

    public Page<Post> paginateOrderByReadNumDesc(int page, int pageSize, PostPaginateQuery query) {
        return paginate(page, pageSize, "readNum", Sort.Direction.DESC, query);
    }

    public Page<Post> paginate(int pageNo, int pageSize, String orderField, Sort.Direction direction, PostPaginateQuery query) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderField));
        Page<Post> page = postRepository.findAll(new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (null != query && query.getUserId() > 0) {
                    Predicate p1 = criteriaBuilder.equal(root.get("user_id").as(String.class), query.getUserId());
                    criteriaQuery.where(criteriaBuilder.and(p1));
                }
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return page;
    }

    public void createPost(Post post, String content, User user) {
        Post post1 = new Post();
        post1.setUser(user);
        post1.setTitle(post.getTitle());
        post1.setContent(content);
        post1.setPublishedAt(post.getPublishedAt());
        postRepository.save(post1);
    }

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

}
