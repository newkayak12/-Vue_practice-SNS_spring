package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import com.vue.vue_practicesns_backend.common.annotation.Encrypt;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElement;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import com.vue.vue_practicesns_backend.service.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://newkayak.iptime.org:8090" )
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private BCryptPasswordEncoder cryptPasswordEncoder;
    private UserService userService;
    @Autowired
    UserController(BCryptPasswordEncoder cryptPasswordEncoder, UserService userService){
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.userService = userService;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    @Encrypt
    public Map signIn(String userId, String password, String authorization) throws NoSuchElement {
        Map result = new HashMap();
        User user = userService.signIn(userId, cryptPasswordEncoder.encode(password));
        if(!cryptPasswordEncoder.matches(password, user.getPassword())){
            throw new NoSuchElement("아이디 혹은 비밀번호가 다릅니다.");
        }
        result.put("userData", user );
        result.put("authorization", authorization);
        return result;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @Encrypt
    public Map signUp(String userId, String password, User user, String authorization) {
        Map result = new HashMap();
        user.setPassword(cryptPasswordEncoder.encode(password));
        userService.signUp(user);
        result.put("userData", user);
        result.put("authorization", authorization);
        return result;
    }
}
