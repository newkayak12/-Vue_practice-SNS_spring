package com.vue.vue_practicesns_backend.repository.dao.user;

import com.querydsl.jpa.impl.JPAQuery;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDao {
    @PersistenceContext
    EntityManager em;

    private JPAQuery<?> jpaQuery;
    UserDao(){
        jpaQuery = new JPAQuery<>(em);
    }


}
