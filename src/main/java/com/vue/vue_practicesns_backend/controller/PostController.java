package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import com.vue.vue_practicesns_backend.repository.dto.PostDto;
import com.vue.vue_practicesns_backend.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://newkayak.iptime.org:9080" )
@RestController
@Slf4j
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private  PostService postService;

    @RequestMapping(value = "/fetchPosts", method = RequestMethod.GET)
    public Map fetchPosts(@RequestParam Long userNo, @RequestParam Integer page, @RequestParam Integer limit){
        return postService.fetchPosts(userNo, page, limit);
    }

    @RequestMapping(value = "/writePost", method = RequestMethod.POST)
    @Authenticate
    public PostDto writePost(@RequestHeader(value = "Authorization") Object authorization, @RequestBody PostDto post){
        return postService.writePost((Map) authorization, post);
    }

}
