package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(WebRouter.HOME)
    public String index() {
        return "home/index";
    }

    @GetMapping(WebRouter.HOME_PROFILE)
    public String profile() {
        return "home/profile";
    }

    @PostMapping(WebRouter.HOME_PROFILE)
    public RedirectView saveProfile(RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "weibo", defaultValue = "") String weibo,
                                    @RequestParam(name = "github", defaultValue = "") String github,
                                    @RequestParam(name = "qq", defaultValue = "") String qq,
                                    @RequestParam(name = "intro", defaultValue = "") String intro) {
        User user = userService.getUser();
        user.setWeibo(weibo);
        user.setGithub(github);
        user.setQq(qq);
        user.setIntro(intro);
        userRepository.save(user);

        return success(WebRouter.HOME_PROFILE, "资料编辑成功", redirectAttributes);
    }

}
