package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(WebRouter.TAG_INDEX)
    public String index(ModelMap modelMap) {
        List<Tag> tags = tagService.findAll();
        modelMap.addAttribute("tags", tags);
        return "tag/index";
    }

}
