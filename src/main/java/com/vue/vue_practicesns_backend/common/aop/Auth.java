package com.vue.vue_practicesns_backend.common.aop;

import com.vue.vue_practicesns_backend.common.auth.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class Auth {

    TokenManager tokenManager = new TokenManager();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Around("@annotation(com.vue.vue_practicesns_backend.common.annotation.Authenticate)")
    public Object  authenticate (ProceedingJoinPoint proceedingJoinPoint) throws Throwable, IllegalAccessException {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] parameterValue =  proceedingJoinPoint.getArgs();
        for( int i = 0; i< signature.getMethod().getParameters().length; i++){
            if(signature.getMethod().getParameters()[i].getName().equals("authorization")){
                parameterValue[i] = tokenManager.decryptToken((String) parameterValue[i], Const.salt);
                break;
            }
        }
       return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After("@annotation(com.vue.vue_practicesns_backend.common.annotation.Encrypt))")
    public void Encrypt(JoinPoint joinPoint) throws  Throwable, IllegalAccessException{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

    }
}
