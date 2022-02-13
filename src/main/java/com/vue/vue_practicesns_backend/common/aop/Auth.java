package com.vue.vue_practicesns_backend.common.aop;

import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class Auth {

    TokenManager tokenManager = new TokenManager();


    @Around("@annotation(com.vue.vue_practicesns_backend.common.annotation.Authenticate)")
//    @Before("execution(public * com.vue.vue_practicesns_backend.controller.UserController.*(..))")
    public Object  authenticate (ProceedingJoinPoint proceedingJoinPoint) throws Throwable, IllegalAccessException {
       log.warn("JOINPOINT {} :", "HELLO");

       return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
}
