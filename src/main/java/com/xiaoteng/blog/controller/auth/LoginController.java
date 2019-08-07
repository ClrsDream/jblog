package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.utils.HashTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public RedirectView loginHandle(RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "email", defaultValue = "") String email,
                                    @RequestParam(name = "password", defaultValue = "") String password) {
        if (email.length() == 0 || password.length() == 0) {
            return super.error("/auth/login", "邮箱和密码不能为空", redirectAttributes);
        }
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return super.error("/auth/login", "邮箱不存在", redirectAttributes);
        }
        User user = optionalUser.get();
        logger.info("current_login_user", user);
        // 判断密码
        if (!HashTool.check(password, user.getPassword())) {
            return super.error("/auth/login", "密码错误", redirectAttributes);
        }
        return success("/", "登录成功", redirectAttributes);
    }

}
