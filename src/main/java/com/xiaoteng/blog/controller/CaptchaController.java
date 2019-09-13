package com.xiaoteng.blog.controller;

import com.wf.captcha.utils.CaptchaUtil;
import com.xiaoteng.blog.router.WebRouter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CaptchaController {

    @GetMapping(WebRouter.CAPTCHA_IMAGE)
    public void imageCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        CaptchaUtil.out(httpServletRequest, httpServletResponse);
    }

}
