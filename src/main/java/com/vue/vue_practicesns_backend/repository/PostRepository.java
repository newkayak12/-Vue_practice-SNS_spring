package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import com.vue.vue_practicesns_backend.repository.postCustomRepository.PostCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    public Page<Post> findPostsByUser(User user, Pageable pageable);
}