package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.repository.entity.ttest.Tests;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Tests test(){
        testService.insertTest( Tests.builder().name("test1").date(new Date()).build() );
    }

}
