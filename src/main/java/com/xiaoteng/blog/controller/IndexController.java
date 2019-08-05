package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(ModelMap modelMap,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<Post> posts = postService.getPostPaginate(page);
        modelMap.addAttribute("posts", posts);
        return "index/index";
    }

}
