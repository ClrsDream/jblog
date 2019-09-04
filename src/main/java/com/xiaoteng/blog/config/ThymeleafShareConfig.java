package com.xiaoteng.blog.config;

import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;

@Component
public class ThymeleafShareConfig {

    @Resource
    public void share(ThymeleafViewResolver viewResolver) {
        System.out.println("running");
        if (viewResolver != null) {
            viewResolver.addStaticVariable("router", new WebRouter());
            viewResolver.addStaticVariable("userService", new UserService());
        }
    }

}
