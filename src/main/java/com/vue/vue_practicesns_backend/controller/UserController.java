package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    public Map singUp(String userId, String Password, String userName, Date birth){
        return null;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    @Authenticate
    public Map SignIn(String userId, String password){

        Map map = new HashMap();
        map.put("test","HELLO");
        map.put("userId", userId);
        map.put("password", password);
        return map;
    }
}
