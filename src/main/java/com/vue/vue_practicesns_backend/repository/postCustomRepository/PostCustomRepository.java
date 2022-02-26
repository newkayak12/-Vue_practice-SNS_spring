package com.vue.vue_practicesns_backend.repository.postCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCustomRepository {
    public List<Post> fetchPosts(Long userNo, Pageable pageable);
}
