package com.vue.vue_practicesns_backend.service;

import com.vue.vue_practicesns_backend.common.auth.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import com.vue.vue_practicesns_backend.repository.UserRepository;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private TokenManager tokenManager;
    private UserRepository userRepository;
    private BCryptPasswordEncoder cryptPasswordEncoder;
    private ModelMapper modelMapper;

    @Transactional(rollbackOn = {Exception.class})
    public Map signUp(UserDto userDto) {
        UserDto dto = userDto;
        dto.setPassword(cryptPasswordEncoder.encode(userDto.getPassword()));
        User user = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .birth(dto.getBirth()).build();
        dto.setUserNo(userRepository.save(user).getUserNo());
        return getAccessToken(dto);
    }


    public Map signIn(UserDto userDto) throws IllegalAccessException{
        UserDto dto = userDto;
        User userEntity = userRepository.signIn(userDto);
        modelMapper.map(userEntity, dto);
        if(!cryptPasswordEncoder.matches(userDto.getPassword(), userEntity.getPassword())){
            throw new IllegalAccessException("아이디 혹은 비밀번호를 확인하세요");
        }
        return getAccessToken(dto);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Map getAccessToken(UserDto userDto){
        Map result = new HashMap();
        result.put("accessToken", tokenManager.encryptToken(userDto, Const.salt));
        result.put("userData", userDto);
        return result;
    }
}
