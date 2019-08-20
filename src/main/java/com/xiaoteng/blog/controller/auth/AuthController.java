package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.enums.UserStatusEnum;
import com.xiaoteng.blog.jobs.SendWelcomeEmailJob;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.utils.HashTool;
import com.xiaoteng.blog.utils.ThreadPool;
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
import java.util.Optional;

@Controller
public class AuthController extends BaseController {

    public final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public RedirectView registerHandle(RedirectAttributes redirectAttributes,
                                       @Valid @ModelAttribute User user,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("/register", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), redirectAttributes);
        }
        // 检测邮箱是否已经注册
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return error("/register", "该邮箱已经注册", redirectAttributes);
        }
        // 创建用户
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(HashTool.encode(user.getPassword()));
        // 用户状态，默认正常
        newUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        userRepository.save(newUser);

        // 发送欢迎邮件
        ThreadPool.push(new SendWelcomeEmailJob(newUser.getEmail()));

        return success("/login", "注册成功，请登录", redirectAttributes);
    }

}
