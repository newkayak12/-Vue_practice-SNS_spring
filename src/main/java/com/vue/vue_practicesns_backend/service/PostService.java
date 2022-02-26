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

import javax.transaction.Transactional;
import java.util.HashMap;
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
        result.put("contents", pages.getContent());
        result.put("pageInfo",pages.getPageable());
        result.put("hasNext",pages.hasNext());
        return result;
    }

    public PostDto writePost(Map authorization, PostDto post) {
        PostDto result = post;
        User userEntity = userRepository.beforeChange(authorization);
        Post postEntity = new Post();
        modelMapper.map(result, postEntity);
        postEntity.setUser(userEntity);
        postRepository.save(postEntity);
        modelMapper.map(postEntity, result);
        return result;
    }
}
