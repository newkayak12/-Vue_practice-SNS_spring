package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
