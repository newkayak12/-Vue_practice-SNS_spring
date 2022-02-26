package com.vue.vue_practicesns_backend.repository.followCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import com.vue.vue_practicesns_backend.repository.entity.user.User;

public interface FollowCustomRepository {
    Follow findByFromAndTo(User from, User to);
}
