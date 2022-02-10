package com.vue.vue_practicesns_backend.repository.user;

import com.querydsl.jpa.impl.JPAQuery;
import static com.vue.vue_practicesns_backend.web.domain.user.QUser.user;
import com.vue.vue_practicesns_backend.web.domain.user.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDao {
    @PersistenceContext
    EntityManager em;

    private JPAQuery<?> jpaQuery;
    UserDao(){
        jpaQuery = new JPAQuery<>(em);
    }

    public User fetchUserOne(){
        return jpaQuery.select(user).from(user).fetchOne();
    }

}
