package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.repository.entity.user.User;
import com.vue.vue_practicesns_backend.repository.userCustomRepository.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    public Integer countUserByUserId(String userId);
    public List<User> searchUsersByUserNameOrUserId(String userName, String userId);
}