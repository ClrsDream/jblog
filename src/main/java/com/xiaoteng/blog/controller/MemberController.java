package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.exceptions.MemberNotFoundException;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.service.query.PostQuery;
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
public class MemberController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Value("${blog.member.page-size}")
    private int pageSize;

    @GetMapping(WebRouter.MEMBER_DETAIL)
    public String detail(ModelMap modelMap,
                         @PathVariable(name = "id") Long id,
                         @RequestParam(name = "page", defaultValue = "1") Integer page) throws MemberNotFoundException {
        User user = userService.findUserById(id);
        if (null == user) {
            throw new MemberNotFoundException();
        }
        page = page <= 0 ? 1 : page;
        PostQuery postQuery = new PostQuery();
        postQuery.setUser(user);
        List<Post> posts = postService.paginateOrderByPublishedAtDesc(page, pageSize, postQuery);
        Long count = postService.count(postQuery);
        PageHelper pageHelper = new PageHelper(count, page, pageSize);

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("pageHelper", pageHelper);
        return "member/detail";
    }

}
