package com.vue.vue_practicesns_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(){
        return "HELLO";
    }

}
