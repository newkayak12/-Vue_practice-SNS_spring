package com.vue.vue_practicesns_backend.common.aop;

import com.vue.vue_practicesns_backend.common.auth.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class Auth {

    TokenManager tokenManager = new TokenManager();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Around("@annotation(com.vue.vue_practicesns_backend.common.annotation.Authenticate)")
//    @Before("execution(public * com.vue.vue_practicesns_backend.controller.UserController.*(..))")
    public Object  authenticate (ProceedingJoinPoint proceedingJoinPoint) throws Throwable, IllegalAccessException {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] parameterValue =  proceedingJoinPoint.getArgs();
        String token = "";
        int i = 0;
        for( i = 0; i< signature.getMethod().getParameters().length; i++){
            if(signature.getMethod().getParameters()[i].getName().equals("authorization")){
                token = (String) parameterValue[i];
                break;
            }
        }
        Claims claims = tokenManager.decryptToken(token, Const.salt);
       return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @Around("@annotation(com.vue.vue_practicesns_backend.common.annotation.Encrypt))")
    public void Encrypt(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable, IllegalAccessException{
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] parameterValue =  proceedingJoinPoint.getArgs();
        String userId = null;
        String password = null;
        String salt = Const.salt;
        Integer authorization = null;


        int i = 0;
        for( i = 0; i< signature.getMethod().getParameters().length; i++){
            if(signature.getMethod().getParameters()[i].getName().equals("authorization")){
                authorization = i;
            }
            if(signature.getMethod().getParameters()[i].getName().equals("userId")){
                userId = (String) parameterValue[i];
            }
            if(signature.getMethod().getParameters()[i].getName().equals("password")){
                password = encoder.encode((String) parameterValue[i]);
            }
        }
        try{
            if(userId==null || password ==null){
                throw new NullPointerException();
            }
            String token = tokenManager.encryptToken(userId,password, salt );
            parameterValue[authorization] = token;
        } catch (NullPointerException e){
            throw  new IllegalAccessException("잘못된 접근입니다.");
        }

        proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
}
