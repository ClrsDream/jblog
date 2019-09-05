package com.xiaoteng.blog.aspects;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.service.PostService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Aspect
@Component
public class PostReadNumAspect {

    private final static Logger log = LoggerFactory.getLogger(PostReadNumAspect.class);
    @Autowired
    private PostService postService;

    @Pointcut("@annotation(com.xiaoteng.blog.annotations.PostReadNumInc)")
    public void p() {

    }

    @After("p()")
    public void doAfter(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 0) {
            return;
        }
        // post阅读数量增加
        ModelMap modelMap = (ModelMap) joinPoint.getArgs()[0];
        Post post = (Post) modelMap.get("post");
        postService.readNumInc(post);
    }

}
