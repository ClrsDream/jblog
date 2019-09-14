package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private PostService postService;

    @Value("${blog.index.page-size}")
    private Integer pageSize;

    @GetMapping(WebRouter.INDEX_PARAM)
    public String indexParam(ModelMap modelMap,
                             @PathVariable(name = "type") String sortType,
                             @RequestParam(name = "page", defaultValue = "1") Integer page) {
        page = page <= 0 ? 1 : page;
        List<Post> posts;
        if (sortType.equals("new")) {
            posts = postService.paginateOrderByPublishedAtDesc(page, pageSize, null);
        } else {
            posts = postService.paginateOrderByReadNumDesc(page, pageSize, null);
        }
        Long count = postService.count(null);
        logger.info("count:{}", count);
        PageHelper pageHelper = new PageHelper(count, page, pageSize);

        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("sortType", sortType);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("pageHelper", pageHelper);
        return "index/index";
    }

    @GetMapping(WebRouter.INDEX)
    public String index(ModelMap modelMap,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        page = page <= 0 ? 1 : page;
        List<Post> posts = postService.paginateOrderByPublishedAtDesc(page, pageSize, null);
        Long count = postService.count(null);
        logger.info("count:{}", count);
        PageHelper pageHelper = new PageHelper(count, page, pageSize);

        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("sortType", "new");
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("pageHelper", pageHelper);

        return "index/index";
    }

}
