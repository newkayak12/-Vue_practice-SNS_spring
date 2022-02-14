package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import com.vue.vue_practicesns_backend.common.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
//    @Authenticate
    @Encrypt
    public Map SignIn(String userId, String password, String authorization){


        log.warn("TOKEN {}", authorization);

        Map map = new HashMap();
        map.put("test","HELLO");
        map.put("userId", userId);
        map.put("password", password);
        return map;
    }
}
