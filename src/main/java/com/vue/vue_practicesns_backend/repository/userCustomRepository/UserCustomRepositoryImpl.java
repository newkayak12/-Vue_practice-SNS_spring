package com.vue.vue_practicesns_backend.repository.userCustomRepository;

import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.vue.vue_practicesns_backend.repository.entity.user.QUser.user;
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User signIn(UserDto userDto) {
        return from(user).where(user.userId.eq(userDto.getUserId())).fetchOne();
    }
}
