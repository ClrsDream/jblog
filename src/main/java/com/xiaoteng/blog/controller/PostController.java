package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.annotations.CaptchaImageVerify;
import com.xiaoteng.blog.annotations.PostReadNumInc;
import com.xiaoteng.blog.exceptions.PostNotFoundException;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.service.TagService;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class PostController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @GetMapping(WebRouter.POST_CREATE)
    public String create() {
        return "post/create";
    }

    @PostMapping(WebRouter.POST_CREATE)
    @CaptchaImageVerify
    public RedirectView store(RedirectAttributes redirectAttributes,
                              @Valid @ModelAttribute Post post,
                              BindingResult bindingResult,
                              @RequestParam(name = "tagStr", defaultValue = "") String tagStr) {
        if (bindingResult.hasErrors()) {
            return error(WebRouter.POST_CREATE, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), redirectAttributes);
        }
        String content = Helper.clean(post.getContent());
        if (content.isEmpty()) {
            // 过滤xss之后为空
            return error(WebRouter.POST_CREATE, "文章内容不能为空", redirectAttributes);
        }
        String[] tagArr = tagStr.split(" ", 5);
        log.info("{}", tagArr);
        postService.createPost(post, content, userService.getUser(), tagArr);

        return super.success(WebRouter.INDEX, "添加成功", redirectAttributes);
    }

    @GetMapping(WebRouter.POST_DETAIL)
    @PostReadNumInc
    public String detail(ModelMap modelMap,
                         @PathVariable("id") Long id) {
        Post post = postService.findById(id);
        if (null == post) {
            throw new PostNotFoundException();
        }
        // 获取tags
        List<Tag> tags = tagService.selectTags(post.getId());
        // 获取喜欢的用户集合
        List<User> favUsers = userService.selectFavUsers(post.getId());

        modelMap.addAttribute("post", post);
        modelMap.addAttribute("tags", tags);
        modelMap.addAttribute("favUsers", favUsers);
        return "/post/detail";
    }

}
