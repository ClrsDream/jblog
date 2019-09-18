package com.xiaoteng.blog.config;

import com.xiaoteng.blog.library.shiro.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/t/new", "anon");
        chainDefinition.addPathDefinition("/t/hot", "anon");
        chainDefinition.addPathDefinition("/tags", "anon");
        chainDefinition.addPathDefinition("/tag/*", "anon");
        chainDefinition.addPathDefinition("/register", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
        // 帖子界面
        chainDefinition.addPathDefinition("/post/*", "anon");
        // 图片验证码
        chainDefinition.addPathDefinition("/captcha/image", "anon");
        chainDefinition.addPathDefinition("/password/find", "anon");
        chainDefinition.addPathDefinition("/password/reset", "anon");
        chainDefinition.addPathDefinition("/assets/**", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        // 后台API接口
        chainDefinition.addPathDefinition("/backend/api/**", "anon");

        // 剩下的都需要登录检测
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    @Bean
    public Realm getShiroRealm() {
        return new ShiroRealm();
    }

}
