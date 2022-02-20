package com.vue.vue_practicesns_backend.repository.userCustomRepository;

import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;

public interface UserCustomRepository  {
    public User signIn(UserDto userDto);
}
