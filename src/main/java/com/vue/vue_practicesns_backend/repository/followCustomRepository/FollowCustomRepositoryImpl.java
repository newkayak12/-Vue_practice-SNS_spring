package com.vue.vue_practicesns_backend.repository.followCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.vue.vue_practicesns_backend.repository.entity.follow.QFollow.follow;

public class FollowCustomRepositoryImpl extends QuerydslRepositorySupport implements FollowCustomRepository {
    public FollowCustomRepositoryImpl() {
        super(Follow.class);
    }

    @Override
    public Follow findByFromAndTo(User from, User to) {
        return from(follow)
                .where(follow.fromNo.eq(from).and(follow.toNo.eq(to)))
                .fetchOne();
    }
}
