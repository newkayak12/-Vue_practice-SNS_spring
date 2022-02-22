package com.vue.vue_practicesns_backend.repository.userCustomRepository;

import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;

import java.util.List;
import java.util.Map;

public interface UserCustomRepository  {
    public User signIn(UserDto userDto);
    public User beforeChange(Map map);
    public List<User> fetchFollowings(Long userNo);
    List<User> fetchFollowers(Long userNo);
}
