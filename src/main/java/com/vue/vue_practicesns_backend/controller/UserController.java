package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import com.vue.vue_practicesns_backend.common.exceptions.DuplicateException;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElementException;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import com.vue.vue_practicesns_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://newkayak.iptime.org:8090" )
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public Map signUp(@RequestBody  UserDto userDto){
        return userService.signUp(userDto);
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public Map signIn(@RequestParam UserDto userDto) throws IllegalAccessException{
        return  userService.signIn(userDto);
    }
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public Map signOut(){
        Map map = new HashMap();
        map.put("userData","");
        map.put("accessKey","");
        return map;
    }
    @RequestMapping(value = "/changeProfile", method = RequestMethod.PATCH)
    @Authenticate
    public Map changeProfile(@RequestHeader(value = "Authorization") Object authorization,
                             @RequestBody UserDto user,
                             @RequestPart(required = false) MultipartFile profileMultiPart,
                             @RequestPart(required = false) MultipartFile backgroundMultiPart) throws NoSuchElementException {
        log.warn("auth {}", authorization);
        return userService.changeProfile((Map) authorization, user, profileMultiPart, backgroundMultiPart);
    }
    @RequestMapping(value = "/changePassword", method = RequestMethod.PATCH)
    @Authenticate
    public Map changePassword(@RequestHeader(value = "Authorization") Object authorization, @RequestBody Map passwordSet) throws NoSuchElementException {
        return userService.changePassword((Map) authorization, passwordSet);
    }
    @RequestMapping(value = "/addFollow", method = RequestMethod.PATCH)
    @Authenticate
    public UserDto addFollow(@RequestHeader(value = "Authorization") Object authorization, @RequestBody Map follow) throws NoSuchElementException, DuplicateException {
        return userService.addFollow((Map) authorization, follow);
    }
    @RequestMapping(value = "/deleteFollow", method = RequestMethod.PATCH)
    @Authenticate
    public Map deleteFollow(@RequestHeader(value = "Authorization") Object authorization, @RequestBody Long userNo) throws  NoSuchElementException, DuplicateException{
        return userService.deleteFollow((Map) authorization, userNo);
    }

    @RequestMapping(value = "/fetchFollowings", method = RequestMethod.GET)
    public List<User> fetchFollowings(@RequestParam Long userNo) {
        return userService.fetchFollowings(userNo);
    }

    @RequestMapping(value = "/fetchFollowers", method = RequestMethod.GET)
    public List<User> fetchFollowers(@RequestParam Long userNo){
        return userService.fetchFollowers(userNo);
    }
}
