package com.vue.vue_practicesns_backend.service;

import com.vue.vue_practicesns_backend.common.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import com.vue.vue_practicesns_backend.common.exceptions.DuplicateException;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElementException;
import com.vue.vue_practicesns_backend.repository.FileUpload;
import com.vue.vue_practicesns_backend.repository.FollowRepository;
import com.vue.vue_practicesns_backend.repository.UserRepository;
import com.vue.vue_practicesns_backend.repository.dto.ImageDto;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import com.vue.vue_practicesns_backend.repository.entity.image.Image;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileUpload fileUpload;
    @Autowired
    private FollowRepository followRepository;

    @Transactional(rollbackOn = {Exception.class})
    public Map getAccessToken(UserDto userDto){
        Map result = new HashMap();
        result.put("accessToken", tokenManager.encryptToken(userDto, Const.salt));
        result.put("userData", userDto);
        log.warn("reuslt {}",result);
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
                .phone(dto.getPhone())
                .birth(dto.getBirth()).build();
        dto.setUserNo(userRepository.save(user).getUserNo());
        return getAccessToken(dto);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Map signIn(UserDto userDto) throws IllegalAccessException{
        UserDto dto = userDto;
        User userEntity = userRepository.signIn(userDto);
        if(!cryptPasswordEncoder.matches(userDto.getPassword(), userEntity.getPassword())){
            throw new IllegalAccessException("아이디 혹은 비밀번호를 확인하세요");
        }
        modelMapper.map(userEntity, dto);
        return getAccessToken(dto);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Map changeProfile(Map authorization, UserDto user, MultipartFile profileMultiPart, MultipartFile backgroundMultiPart) throws NoSuchElementException {
        User userEntity = userRepository.beforeChange(authorization);
        UserDto dto = user;
        if(userEntity == null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }
        if(profileMultiPart!= null && !profileMultiPart.isEmpty()){
            ImageDto profileDto = fileUpload.fileUpload(profileMultiPart);
            Image profileEntity = Image.builder()
                    .imageName(profileDto.getImageName())
                    .imagePath(profileDto.getImagePath())
                    .build();
            userEntity.setProfileImage(profileEntity);
        }
        if(backgroundMultiPart!=null && !backgroundMultiPart.isEmpty()){
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
    public UserDto addFollow(Map authorization, Map follow) throws NoSuchElementException, DuplicateException {
        User userEntity = userRepository.beforeChange(authorization);
        User targetEntity = userRepository.beforeChange(follow);
        UserDto dto = new UserDto();
        if(userEntity==null || targetEntity==null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }

//        userEntity.addFollow(Follow.builder().toNo(targetEntity).build());
//        log.warn("fol");
//        userEntity.getFollowing().stream().forEach(System.out::println);
//        userRepository.save(userEntity);
//        userRepository.saveAndFlush(userEntity);
        Follow following = new Follow();
        following.setFromNo(userEntity);
        following.setToNo(targetEntity);
        followRepository.save(following);
//        userEntity.addFollowing(following);
//        targetEntity.addedFollower(following);

        modelMapper.map(userEntity, dto);
        return dto;
    }
    @Transactional(rollbackOn = {Exception.class})
    public Map deleteFollow(Map authorization, Long userNo) throws NoSuchElementException{
        User userEntity = userRepository.beforeChange(authorization);
        Map follow = new HashMap();
        follow.put("userNo", userNo);
        UserDto dto = new UserDto();
        User targetEntity = userRepository.beforeChange(follow);
        if(userEntity==null){
            throw new NoSuchElementException("잘못된 접근입니다.");
        }
//        userEntity.deleteFollow(targetEntity);
        userRepository.save(userEntity);
        modelMapper.map(userEntity, dto);
        return getAccessToken(dto);
    }
    public List<User> fetchFollowings(Long userNo) {
        return userRepository.fetchFollowings(userNo);
    }
    public List<User> fetchFollowers(Long userNo) {
        return userRepository.fetchFollowers(userNo);
    }

}
