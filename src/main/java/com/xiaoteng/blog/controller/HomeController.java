package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.utils.HashTool;
import com.xiaoteng.blog.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("active", "index");
        return "home/index";
    }

    @GetMapping(WebRouter.HOME_PROFILE)
    public String profile(ModelMap modelMap) {
        modelMap.addAttribute("active", "profile");
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

}
