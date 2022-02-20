package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.common.annotation.Authenticate;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://newkayak.iptime.org:8090" )
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
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
                             @RequestPart MultipartFile profileMultiPart,
                             @RequestPart MultipartFile backgroundMultiPart){

    }
}
