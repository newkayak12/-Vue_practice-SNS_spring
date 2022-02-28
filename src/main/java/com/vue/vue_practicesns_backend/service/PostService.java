package com.vue.vue_practicesns_backend.service;

import com.vue.vue_practicesns_backend.repository.PostRepository;
import com.vue.vue_practicesns_backend.repository.UserRepository;
import com.vue.vue_practicesns_backend.repository.dto.PostDto;
import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Map fetchPosts(Long userNo, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page,limit, Sort.by("createdDate").descending());
        Page<Post> pages = postRepository.findPostsByUser(userRepository.findById(userNo).get(), pageable);
        Map result = new HashMap();
        List<PostDto> list = new ArrayList<>();
//        result.put("contents", pages.getContent());
        pages.getContent().stream().forEach(v->{
            PostDto dto = new PostDto();
            modelMapper.map(v, dto);
            list.add(dto);
        });
        result.put("contents", list);
        result.put("pageInfo",pages.getPageable());
        result.put("hasNext",pages.hasNext());
        return result;
    }
    @Transactional(rollbackOn = {Exception.class})
    public PostDto writePost(Map authorization, PostDto post) throws IllegalAccessException {
        PostDto result = post;
        User userEntity = userRepository.beforeChange(authorization);
        if(userEntity==null) {
            throw  new IllegalAccessException("잘못된 접근입니다.");
        }
        Post postEntity = new Post();
        modelMapper.map(result, postEntity);
        postEntity.setUser(userEntity);
        postRepository.save(postEntity);
        modelMapper.map(postEntity, result);
        return result;
    }
    @Transactional(rollbackOn = {Exception.class})
    public PostDto modifyPost(Map authorization, PostDto post) throws IllegalAccessException {
        PostDto dto = post;
        User userEntity = userRepository.beforeChange(authorization);
        if(userEntity==null){
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        Post postEntity = postRepository.getById(dto.getPostNo());
        if(dto.getContent() != null && !StringUtils.isEmpty(dto.getContent())){
            postEntity.setContent(dto.getContent());
        }
        if(dto.getIsMain() != null&& !StringUtils.isEmpty(dto.getIsMain())){
            postEntity.setIsMain(dto.getIsMain());
        }
        if(dto.getMovieLink() != null&&!StringUtils.isEmpty(dto.getMovieLink())){
            postEntity.setMovieLink(dto.getMovieLink());
        }
        if(dto.getOuterLink() != null&&!StringUtils.isEmpty(dto.getOuterLink())){
            postEntity.setOuterLink(dto.getOuterLink());
        }
        postRepository.save(postEntity);

        modelMapper.map(postEntity, dto);
        return dto;
    }

    public PostDto removePost(Map authorization, PostDto post) throws IllegalAccessException {
        User userEntity = userRepository.beforeChange(authorization);
        if(userEntity==null){
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        PostDto dto = post;
        Post postEntity = postRepository.getById(dto.getPostNo());
        postRepository.delete(postEntity);
        dto = null;
        return dto;
    }

    public PostDto addLikePost(Map authorization, PostDto post) throws IllegalAccessException {
        Post postEntity = postRepository.getById(post.getPostNo());
        User userEntity = userRepository.getById(Long.parseLong(authorization.get("userNo").toString()));
        if(postEntity.getPostNo()==null){
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        if(postEntity.getUser().getUserNo()==userEntity.getUserNo()){
            throw  new IllegalAccessException("잘못된 접근입니다.");
        }
        postEntity.getLikedUser().add(userEntity);
        postRepository.save(postEntity);
        PostDto dto = new PostDto();
        modelMapper.map(postEntity, dto);
        return dto;
    }

    public PostDto removeLikePost(Map authorization, PostDto post) throws IllegalAccessException {
        Post postEntity = postRepository.getById(post.getPostNo());
        User userEntity = userRepository.getById(Long.parseLong(authorization.get("userNo").toString()));
        if(postEntity.getPostNo()==null){
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        if(postEntity.getUser().getUserNo()==userEntity.getUserNo()){
            throw  new IllegalAccessException("잘못된 접근입니다.");
        }
        postEntity.getLikedUser().remove(userEntity);
        postRepository.save(postEntity);
        PostDto dto = new PostDto();
        modelMapper.map(postEntity, dto);
        return dto;
    }
}
