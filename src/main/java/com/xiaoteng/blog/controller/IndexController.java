package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(ModelMap modelMap,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        page = page <= 0 ? 1 : page;
        page -= 1;
        Page<Post> posts = postService.getPostPaginate(page);
        logger.info("posts", posts);
        modelMap.addAttribute("posts", posts);
        return "index/index";
    }

}
