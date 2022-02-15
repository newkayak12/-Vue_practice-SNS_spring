package com.vue.vue_practicesns_backend.service.userService;

import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElement;
import com.vue.vue_practicesns_backend.repository.dao.user.UserDao;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private UserDao userDao;

    @Autowired
    UserService(UserDao userDao){
        this.userDao = userDao;
    }
    public User signIn(String userId, String password) throws NoSuchElement {
        User userData = userDao.signIn(userId, password);
        if(userData==null){
            throw new NoSuchElement("해당 계정은 존재하지 않습니다.");
        }
        return userData;
    }

    public void signUp(User user) {
        userDao.signUp(user);
    }
}
