package com.xiaoteng.blog.aspects;

import com.wf.captcha.utils.CaptchaUtil;
import com.xiaoteng.blog.utils.Helper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CaptchaImageVerify {

    private final static Logger log = LoggerFactory.getLogger(CaptchaImageVerify.class);

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.xiaoteng.blog.annotations.CaptchaImageVerify)")
    public Object doBefore(ProceedingJoinPoint joinPoints) throws Throwable {
        // 获取前端传递的验证码
        String imageCaptcha = request.getParameter("image_captcha");
        log.info("imageCaptchaCode:{}", imageCaptcha);
        if (!CaptchaUtil.ver(imageCaptcha, request)) {
            // 清除当前验证码信息，防止同一个验证码碰撞攻击
            CaptchaUtil.clear(request);
            // 图片验证码验证失败
            log.info("imageCaptchaVerifyFail:imageCode:{}", imageCaptcha);
            // 重定向到错误界面
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(request.getRequestURI());
            redirectView.setContextRelative(true);
            // flash错误信息
            RedirectAttributes redirectAttributes = Helper.getRedirectAttributes(joinPoints.getArgs());
            if (redirectAttributes != null) {
                redirectAttributes.addFlashAttribute("error", "图片验证码错误");
            }
            return redirectView;
        }
        return joinPoints.proceed();
    }

}
