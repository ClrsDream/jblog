package com.xiaoteng.blog.aspects;

import com.xiaoteng.blog.controller.api.ApiController;
import com.xiaoteng.blog.enums.ApiErrorEnum;
import com.xiaoteng.blog.exceptions.MemberNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Aspect
@Component
public class ApiAspect {

    @Pointcut("execution(* com.xiaoteng.blog.controller.api..*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    @ResponseBody
    public Object doAround(ProceedingJoinPoint pjp) {
        try {
            Object res = pjp.proceed();
            return res;
        } catch (MemberNotFoundException e) {
            return new ApiController.Response(ApiErrorEnum.MEMBER_NOT_FOUND.getCode(), ApiErrorEnum.MEMBER_NOT_FOUND.getMessage(), null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new ApiController.Response(ApiErrorEnum.SYSTEM_ERROR.getCode(), ApiErrorEnum.SYSTEM_ERROR.getMessage(), null);
        }
    }

}
