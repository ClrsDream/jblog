package com.xiaoteng.blog.aspects;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.service.PostService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostReadNumAspect {

    private final static Logger log = LoggerFactory.getLogger(PostReadNumAspect.class);
    @Autowired
    private PostService postService;

    @Pointcut("@annotation(com.xiaoteng.blog.annotations.PostReadNumInc)")
    public void p() {

    }

    @Around("p()")
    public Object doAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object res = proceedingJoinPoint.proceed();

        // after之后处理
        Object[] objects = proceedingJoinPoint.getArgs();
        Long postId = null;
        for (Object o : objects) {
            if (o instanceof Long) {
                postId = (Long) o;
            }
        }
        if (postId != null) {
            Post post = postService.findById(postId);
            postService.readNumInc(post);
        }

        return res;
    }

}
