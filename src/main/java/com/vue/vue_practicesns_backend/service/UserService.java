package com.vue.vue_practicesns_backend.service;

import com.vue.vue_practicesns_backend.common.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import com.vue.vue_practicesns_backend.common.exceptions.DuplicateException;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElementException;
import com.vue.vue_practicesns_backend.repository.FileUpload;
import com.vue.vue_practicesns_backend.repository.UserRepository;
import com.vue.vue_practicesns_backend.repository.dto.ImageDto;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.image.Image;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    private FileUpload fileUpload;

    @Transactional(rollbackOn = {Exception.class})
    public Map getAccessToken(UserDto userDto){
        Map result = new HashMap();
        result.put("accessToken", tokenManager.encryptToken(userDto, Const.salt));
        result.put("userData", userDto);
        return result;
    }

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

    @Transactional(rollbackOn = {Exception.class})
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
    public Map changeProfile(Map authorization, UserDto user, MultipartFile profileMultiPart, MultipartFile backgroundMultiPart) throws NoSuchElementException {
        User userEntity = userRepository.beforeChange(authorization);
        UserDto dto = user;
        if(userEntity == null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }
        if(profileMultiPart.isEmpty()){
            ImageDto profileDto = fileUpload.fileUpload(profileMultiPart);
            Image profileEntity = Image.builder()
                    .imageName(profileDto.getImageName())
                    .imagePath(profileDto.getImagePath())
                    .build();
            userEntity.setProfileImage(profileEntity);
        }
        if(backgroundMultiPart.isEmpty()){
            ImageDto backgroundDto = fileUpload.fileUpload(backgroundMultiPart);
            Image backgroundEntity = Image.builder()
                    .imageName(backgroundDto.getImageName())
                    .imagePath(backgroundDto.getImagePath())
                    .build();
            userEntity.setProfileImage(backgroundEntity);
        }
        if(!StringUtils.isEmpty(user.getLink())){
            userEntity.setLink(user.getLink());
        }


        userRepository.save(userEntity);
        modelMapper.map(userEntity, dto);
        return getAccessToken(dto);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Map changePassword(Map authorization, Map passwordSet) throws NoSuchElementException {
        User userEntity = userRepository.beforeChange(authorization);
        UserDto dto = new UserDto();
        if(userEntity == null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }
        userEntity.setPassword(cryptPasswordEncoder.encode((String)passwordSet.get("password")));
        userRepository.save(userEntity);
        modelMapper.map(userEntity, dto);
        return getAccessToken(dto);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Map addFollow(Map authorization, Map follow) throws NoSuchElementException, DuplicateException {
        User userEntity = userRepository.beforeChange(authorization);
        User targetEntity = userRepository.beforeChange(follow);
        UserDto dto = new UserDto();
        if(userEntity!=null && targetEntity!=null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }
        userEntity.addFollow(targetEntity);
        userRepository.save(userEntity);
        modelMapper.map(userEntity, dto);
        return getAccessToken(dto);
    }
}
