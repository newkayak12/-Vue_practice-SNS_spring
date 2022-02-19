package com.vue.vue_practicesns_backend.repository.userCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    public UserCustomRepositoryImpl() {
        super(User.class);
    }
}
