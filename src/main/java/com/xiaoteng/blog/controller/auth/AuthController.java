package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class AuthController extends BaseController {

    public final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @GetMapping(WebRouter.REGISTER)
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping(WebRouter.REGISTER)
    public RedirectView registerHandle(RedirectAttributes redirectAttributes,
                                       @Valid @ModelAttribute User user,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(WebRouter.REGISTER, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), redirectAttributes);
        }
        // 检测邮箱是否已经注册
        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            return error(WebRouter.REGISTER, "该邮箱已被注册", redirectAttributes);
        }
        // 检测昵称是否被使用
        User user2 = userService.findUserByNickname(user.getNickname());
        if (user2 != null) {
            return error(WebRouter.REGISTER, "该昵称已被使用", redirectAttributes);
        }
        // 创建用户
        userService.createUser(user);

        return success(WebRouter.LOGIN, "注册成功，请登录", redirectAttributes);
    }

}
