package com.xiaoteng.blog.config;

import com.xiaoteng.blog.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;

public class ShiroUserShareConfig {

    @Bean
    @Resource
    public ThymeleafViewResolver userShare(ThymeleafViewResolver thymeleafViewResolver) {
        if (thymeleafViewResolver != null) {
            // 获取当前登录的用户
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            thymeleafViewResolver.addStaticVariable("user", user);
        }
        return thymeleafViewResolver;
    }

}
