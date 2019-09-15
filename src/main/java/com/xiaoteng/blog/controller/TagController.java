package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.exceptions.TagNotFoundException;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.service.TagService;
import com.xiaoteng.blog.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

    @Value("${blog.tag.page-size}")
    private int pageSize;

    @GetMapping(WebRouter.TAG_INDEX)
    public String index(ModelMap modelMap) {
        List<Tag> tags = tagService.findAll();
        modelMap.addAttribute("tags", tags);
        return "tag/index";
    }

    @GetMapping(WebRouter.TAG_DETAIL)
    public String detail(ModelMap modelMap,
                         @PathVariable(name = "id") Long id,
                         @RequestParam(name = "page", defaultValue = "1") int page) {
        Tag tag = tagService.findById(id);
        if (tag == null) {
            throw new TagNotFoundException();
        }
        page = page < 1 ? 1 : page;
        List<Post> posts = postService.paginateTag(page, pageSize, tag);
        Long count = postService.tagCount(tag);
        PageHelper pageHelper = new PageHelper(count, page, pageSize);
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("pageHelper", pageHelper);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("tag", tag);
        return "tag/detail";
    }

}
