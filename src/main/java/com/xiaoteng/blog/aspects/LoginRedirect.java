package com.xiaoteng.blog.aspects;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginRedirect {

    private final static Logger log = LoggerFactory.getLogger(LoginRedirect.class);

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.xiaoteng.blog.annotations.LoginRedirect)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        User user = userService.getUser();
        log.info("loginRedirect:current_user:{}", user);
        if (user != null) {
            return "redirect:" + WebRouter.INDEX;
        }
        return proceedingJoinPoint.proceed();
    }

}
