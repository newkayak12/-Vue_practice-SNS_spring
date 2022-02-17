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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class UserDao {
    @PersistenceContext
    EntityManager em;
//
//    private JPAQuery<?> jpaQuery = new JPAQuery<>(em);
//
//
//    public User signIn(String userId, String password) {
//        return   jpaQuery.select(user)
//                .from(user)
//                .where(user.userId.eq(userId).and(user.password.eq(password)))
//                .fetchOne();
//    }
//
//    public void signUp(User user) {
//        em.persist(user);
//        em.flush();
//    }
//
//
//    public void signOut(Long userNo) {
//       User _user = jpaQuery.select(user).from(user).where(user.userNo.eq(userNo)).fetchOne();
//       _user.setModifiedDate(LocalDate.now());
//       em.merge(_user);
//    }
//
//    public User addFollowing(Long userNo) {
//        return jpaQuery.select(user).from(user).where(user.userNo.eq(userNo)).fetchOne();
//    }
}
