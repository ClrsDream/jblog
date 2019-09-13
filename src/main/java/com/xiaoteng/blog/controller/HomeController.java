package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.annotations.CaptchaImageVerify;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.service.query.PostQuery;
import com.xiaoteng.blog.utils.HashTool;
import com.xiaoteng.blog.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class HomeController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Value("${blog.member.page-size}")
    private Integer pageSize;

    @GetMapping(WebRouter.HOME)
    public String index(ModelMap modelMap,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        PostQuery postQuery = new PostQuery();
        postQuery.setUser(userService.getUser());
        page = page <= 0 ? 1 : page;
        page -= 1;
        Page<Post> posts = postService.paginateOrderByPublishedAtDesc(page, pageSize, postQuery);
        log.info("查询结果：{}，查询条件：{}", posts, postQuery);
        modelMap.addAttribute("active", "index");
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("page", page);
        return "home/index";
    }

    @GetMapping(WebRouter.HOME_PROFILE)
    public String profile(ModelMap modelMap) {
        User user = userService.findUserById(userService.getUser().getId());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("active", "profile");
        return "home/profile";
    }

    @PostMapping(WebRouter.HOME_PROFILE)
    @CaptchaImageVerify
    public RedirectView saveProfile(RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "weibo", defaultValue = "") String weibo,
                                    @RequestParam(name = "github", defaultValue = "") String github,
                                    @RequestParam(name = "qq", defaultValue = "") String qq,
                                    @RequestParam(name = "intro", defaultValue = "") String intro) {
        User user = userService.getUser();
        user.setWeibo(weibo);
        user.setGithub(github);
        user.setQq(qq);
        user.setIntro(Helper.clean(intro));
        userRepository.save(user);

        return success(WebRouter.HOME_PROFILE, "资料编辑成功", redirectAttributes);
    }

    @GetMapping(WebRouter.HOME_CHANGE_PASSWORD)
    public String changePassword(ModelMap modelMap) {
        modelMap.addAttribute("active", "change_password");
        return "home/changePassword";
    }

    @PostMapping(WebRouter.HOME_CHANGE_PASSWORD)
    @CaptchaImageVerify
    public RedirectView changePasswordHandler(RedirectAttributes redirectAttributes,
                                              @RequestParam(name = "old_password", defaultValue = "") String oldPassword,
                                              @RequestParam(name = "new_password", defaultValue = "") String newPassword,
                                              @RequestParam(name = "new_password_confirmation", defaultValue = "") String newPasswordConfirmation) {
        if (oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordConfirmation.isEmpty()) {
            return error(WebRouter.HOME_CHANGE_PASSWORD, "密码不能为空", redirectAttributes);
        }
        if (!newPassword.equals(newPasswordConfirmation)) {
            return error(WebRouter.HOME_CHANGE_PASSWORD, "两次输入密码不一致", redirectAttributes);
        }
        User user = userService.getUser();
        // 校验原密码
        if (!HashTool.check(oldPassword, user.getPassword())) {
            return error(WebRouter.HOME_CHANGE_PASSWORD, "原密码错误", redirectAttributes);
        }
        // 修改密码
        userService.setPassword(user, newPassword);
        return success(WebRouter.HOME_CHANGE_PASSWORD, "密码修改成功", redirectAttributes);
    }

    @GetMapping(WebRouter.HOME_FAVORITE)
    public String favorite(ModelMap modelMap) {
        User user = userService.findUserById(userService.getUser().getId());
        List<Post> posts = user.getFavoritePosts();
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("active", "favorite");
        return "home/favorite";
    }

    @ResponseBody
    @PostMapping(WebRouter.POST_FAVORITE)
    public String addFavorite(@RequestParam(name = "id", defaultValue = "") Long postId) {
        Post post = postService.findById(postId);
        if (null == post) {
            return "404";
        }
        userService.updateFavPost(userService.getUser().getId(), post);
        return "0";
    }

}
