package com.xiaoteng.blog.repositories;

import com.xiaoteng.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
