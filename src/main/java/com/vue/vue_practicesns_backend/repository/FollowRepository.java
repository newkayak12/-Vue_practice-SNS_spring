package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import com.vue.vue_practicesns_backend.repository.followCustomRepository.FollowCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {
}
