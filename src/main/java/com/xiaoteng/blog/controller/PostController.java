package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.annotations.CaptchaImageVerify;
import com.xiaoteng.blog.annotations.PostReadNumInc;
import com.xiaoteng.blog.config.LimitConfig;
import com.xiaoteng.blog.exceptions.PostNotFoundException;
import com.xiaoteng.blog.jblog.LimitService;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.PostComment;
import com.xiaoteng.blog.model.Tag;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostCommentService;
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

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private LimitService limitService;

    @Autowired
    private LimitConfig limitConfig;

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
        if (!limitService.canRegisterPost(userService.getUser().getId())) {
            return back("新用户需要在自注册" + (limitConfig.getRegisterPost() / 60) + "分钟后才可以发帖", redirectAttributes);
        }
        if (!limitService.canPost(userService.getUser().getId())) {
            return back("请不要频繁发帖", redirectAttributes);
        }
        if (bindingResult.hasErrors()) {
            return back(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), redirectAttributes);
        }
        String content = Helper.clean(post.getContent());
        if (content.isBlank()) {
            // 过滤xss之后为空
            return back("文章内容不能为空", redirectAttributes);
        }
        String[] tagArr = tagStr.split(" ", 5);
        log.info("创建文章收到的标签tags:{}", tagArr);
        postService.createPost(post, content, userService.getUser(), tagArr);

        // 配置limit
        limitService.setPost(userService.getUser().getId());

        return success(WebRouter.INDEX, "添加成功", redirectAttributes);
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
        // 当前用户是否已经喜欢
        boolean fav = false;
        if (userService.getUser() != null) {
            fav = userService.userFavPost(userService.getUser().getId(), post.getId());
        }
        // 是否登录
        boolean isLogin = false;
        if (userService.getUser() != null) {
            isLogin = true;
        }
        // 评论
        List<PostComment> postComments = postCommentService.selectPostComments(post);

        modelMap.addAttribute("post", post);
        modelMap.addAttribute("tags", tags);
        modelMap.addAttribute("fav", fav);
        modelMap.addAttribute("favUsers", favUsers);
        modelMap.addAttribute("postComments", postComments);
        modelMap.addAttribute("isLogin", isLogin);
        return "/post/detail";
    }

}
