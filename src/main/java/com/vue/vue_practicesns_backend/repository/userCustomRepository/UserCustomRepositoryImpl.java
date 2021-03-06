package com.vue.vue_practicesns_backend.repository.userCustomRepository;

import com.querydsl.core.BooleanBuilder;
import com.vue.vue_practicesns_backend.repository.dto.UserDto;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static com.vue.vue_practicesns_backend.repository.entity.follow.QFollow.follow;
import static com.vue.vue_practicesns_backend.repository.entity.user.QUser.user;
@Slf4j
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User signIn(UserDto userDto) {
        return from(user)
                .where(user.userId.eq(userDto.getUserId()))
                .fetchOne();
    }

    @Override
    public User beforeChange(Map map) {
        BooleanBuilder builder = new BooleanBuilder();
        if(!(StringUtils.isEmpty((String)map.get("userId")))){
            builder.and(user.userId.eq((String) map.get("userId")));
        }
        if(map.get("userNo").toString() != null){
            builder.and(user.userNo.eq(Long.parseLong(map.get("userNo").toString())));
        }
         return from(user).where(builder).fetchOne();
    }

    @Override
    public List<User> fetchFollowings(Long userNo) {
        return from(user)
                .leftJoin(user.following, follow)
                .fetchJoin()
                .leftJoin(follow.fromNo, user)
                .fetchJoin()
                .where(user.userNo.eq(userNo))
                .fetch();
    }

//    @Override
//    public List<User> fetchFollowers(Long userNo) {
//        return from(user)
//                .leftJoin(user.follower, follow)
//                .fetchJoin()
//                .leftJoin(follow.toNo, user)
//                .fetchJoin()
//                .where(user.userNo.eq(userNo))
//                .fetch();
//    }
}
