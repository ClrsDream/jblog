package com.xiaoteng.blog.jblog;

import com.xiaoteng.blog.config.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class LimitService {

    private final static String PREFIX_POST = "limit_post:";
    private final static String PREFIX_COMMENT = "limit_comment:";
    private final static String PREFIX_REGISTER_COMMENT = "limit_register_comment:";
    private final static String PREFIX_REGISTER_POST = "limit_register_post:";
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private LimitConfig limitConfig;

    public boolean canPost(Long id) {
        String name = PREFIX_POST + id;
        return limitConfig.getPost() == 0 || go(name);
    }

    public boolean canComment(Long id) {
        String name = PREFIX_COMMENT + id;
        return limitConfig.getComment() == 0 || go(name);
    }

    public boolean canRegisterComment(Long id) {
        String name = PREFIX_REGISTER_COMMENT + id;
        return limitConfig.getRegisterComment() == 0 || go(name);
    }

    public boolean canRegisterPost(Long id) {
        String name = PREFIX_REGISTER_POST + id;
        return limitConfig.getRegisterPost() == 0 || go(name);
    }

    public void setPost(Long id) {
        if (limitConfig.getPost() == 0) {
            return;
        }
        String name = PREFIX_POST + id;
        add(name, limitConfig.getPost());
    }

    public void setComment(Long id) {
        if (limitConfig.getComment() == 0) {
            return;
        }
        String name = PREFIX_COMMENT + id;
        add(name, limitConfig.getComment());
    }

    public void setRegisterPost(Long id) {
        if (limitConfig.getRegisterPost() == 0) {
            return;
        }
        String name = PREFIX_REGISTER_POST + id;
        add(name, limitConfig.getRegisterPost());
    }

    public void setRegisterComment(Long id) {
        if (limitConfig.getRegisterComment() == 0) {
            return;
        }
        String name = PREFIX_REGISTER_COMMENT + id;
        add(name, limitConfig.getRegisterComment());
    }

    public boolean go(String key) {
        String timeS = redis.opsForValue().get(key);
        return timeS == null;
    }

    public void add(String key, int t) {
        redis.opsForValue().set(key, "1", t, TimeUnit.SECONDS);
    }

}
