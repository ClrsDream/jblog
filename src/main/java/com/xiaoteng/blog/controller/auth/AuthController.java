package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.Enums.UserStatusEnum;
import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.utils.HashTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

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
            return error("/auth/register", bindingResult.getFieldError().getDefaultMessage(), redirectAttributes);
        }
        // 检测邮箱是否已经注册
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return error("/auth/register", "该邮箱已经注册", redirectAttributes);
        }
        // 创建用户
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(HashTool.encode(user.getPassword()));
        // 用户状态，默认正常
        newUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        userRepository.save(newUser);

        // TODO 发送欢迎邮件

        return success("/auth/login", "注册成功，请登录", redirectAttributes);
    }

}
