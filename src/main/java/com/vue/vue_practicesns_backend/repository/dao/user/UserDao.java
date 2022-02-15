package com.vue.vue_practicesns_backend.repository.dao.user;

import com.querydsl.core.dml.InsertClause;
import com.querydsl.jpa.hibernate.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;
import static com.vue.vue_practicesns_backend.repository.entity.user.QUser.user;

import com.vue.vue_practicesns_backend.repository.entity.user.QUser;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager em;
    Session session;

    private JPAQuery<?> jpaQuery;
    UserDao(){
        jpaQuery = new JPAQuery<>(em);
    }


    public User signIn(String userId, String password) {
        return   jpaQuery.select(user)
                .from(user)
                .where(user.userId.eq(userId).and(user.password.eq(password)))
                .fetchOne();
    }

    public void signUp(User user) {
        em.persist(user);
        em.flush();
    }
}
