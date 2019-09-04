package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.annotations.LoginRedirect;
import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(WebRouter.LOGIN)
    @LoginRedirect
    public String login() {
        return "auth/login";
    }

    @PostMapping(WebRouter.LOGIN)
    @LoginRedirect
    public RedirectView loginHandle(RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "email", defaultValue = "") String email,
                                    @RequestParam(name = "password", defaultValue = "") String password,
                                    @RequestParam(name = "remember_me", defaultValue = "0") String rememberMe) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return super.error(WebRouter.LOGIN, "邮箱和密码不能为空", redirectAttributes);
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 登录
            currentUser.login(new UsernamePasswordToken(email, password, rememberMe.equals("1")));
            // 读取已登录的用户
            User user = (User) currentUser.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }

            return super.success(WebRouter.INDEX, "登录成功", redirectAttributes);
        } catch (UnknownAccountException uae) {
            return super.error(WebRouter.LOGIN, "邮箱不存在", redirectAttributes);
        } catch (IncorrectCredentialsException ice) {
            return super.error(WebRouter.LOGIN, "密码错误", redirectAttributes);
        } catch (LockedAccountException lae) {
            return super.error(WebRouter.LOGIN, "用户被锁定，请稍后再试", redirectAttributes);
        } catch (AuthenticationException ae) {
            return super.error(WebRouter.LOGIN, "登录失败:" + ae.getMessage(), redirectAttributes);
        }
    }

}
