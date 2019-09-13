package com.xiaoteng.blog.controller.auth;

import com.xiaoteng.blog.annotations.CaptchaImageVerify;
import com.xiaoteng.blog.config.AppConfig;
import com.xiaoteng.blog.controller.BaseController;
import com.xiaoteng.blog.jblog.PasswordFindUtil;
import com.xiaoteng.blog.mails.PasswordFindMail;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.utils.mail.AliyunMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PasswordController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AliyunMail aliyunMail;

    @GetMapping(WebRouter.PASSWORD_FOUND)
    public String showPasswordFindPage() {
        return "auth/password/find";
    }

    @PostMapping(WebRouter.PASSWORD_FOUND)
    @CaptchaImageVerify
    public RedirectView handlePasswordFind(RedirectAttributes redirectAttributes,
                                           @RequestParam(name = "email", defaultValue = "") String email) {
        if (StringUtils.isEmpty(email)) {
            return error(WebRouter.PASSWORD_FOUND, "请输入邮箱", redirectAttributes);
        }
        User user = userService.findUserByEmail(email);
        if (user == null) {
            return error(WebRouter.PASSWORD_FOUND, "邮箱不存在", redirectAttributes);
        }
        PasswordFindMail passwordFindMail = new PasswordFindMail(user);
        passwordFindMail.setAppUrl(appConfig.getUrl());
        passwordFindMail.setSendEmail(aliyunMail);
        boolean sendResult = passwordFindMail.send();
        if (!sendResult) {
            return error(WebRouter.PASSWORD_FOUND, "邮件发送失败，请连接管理员", redirectAttributes);
        }
        return success(WebRouter.PASSWORD_FOUND, "密码重置邮件已发送", redirectAttributes);
    }

    @GetMapping(WebRouter.PASSWORD_RESET)
    public String passwordReset(@RequestParam(name = "email", defaultValue = "") String email,
                                @RequestParam(name = "sign", defaultValue = "") String sign,
                                @RequestParam(name = "timestamp", defaultValue = "") Long timestamp,
                                ModelMap modelMap) {
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("sign", sign);
        modelMap.addAttribute("timestamp", timestamp);
        return "auth/password/reset";
    }

    @PostMapping(WebRouter.PASSWORD_RESET)
    @CaptchaImageVerify
    public RedirectView passwordReset(RedirectAttributes redirectAttributes,
                                      @RequestParam(name = "email", defaultValue = "") String email,
                                      @RequestParam(name = "sign", defaultValue = "") String sign,
                                      @RequestParam(name = "timestamp", defaultValue = "") Long timestamp,
                                      @RequestParam(name = "password", defaultValue = "") String password,
                                      @RequestParam(name = "password_confirmation", defaultValue = "") String passwordConfirmation) {
        log.info("email:{},sign:{},timestamp:{}", email, sign, timestamp);
        if (StringUtils.isEmpty(email)) {
            return back("请填写邮箱", redirectAttributes);
        }
        if (!password.equals(passwordConfirmation)) {
            return back("两次输入密码不一致", redirectAttributes);
        }
        User user = userService.findUserByEmail(email);
        if (user == null) {
            return back("邮箱不存在", redirectAttributes);
        }
        // 校验时间戳
        if (PasswordFindUtil.isTimeout(timestamp)) {
            return back("当前链接已超时，请重新发送邮件", redirectAttributes);
        }
        // 校验sign
        if (!PasswordFindUtil.verify(user, timestamp, sign)) {
            return back("链接非法", redirectAttributes);
        }
        // 重置密码
        userService.setPassword(user, password);
        return success(WebRouter.LOGIN, "密码重置成功，请重新登录", redirectAttributes);
    }

}
