package com.vue.vue_practicesns_backend.service.userService;

import com.vue.vue_practicesns_backend.common.auth.Const;
import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElement;
import com.vue.vue_practicesns_backend.repository.dao.user.UserDao;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class UserService {
    private UserDao userDao;
    private TokenManager tokenManager;

    @Autowired
    UserService(UserDao userDao, TokenManager tokenManager){
        this.userDao = userDao;
        this.tokenManager=tokenManager;
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

    public void signOut(String authorization)  {
        try {
            Map claims = tokenManager.decryptToken(authorization, Const.salt);
            userDao.signOut((Long) claims.get("userNo"));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public User addFollowing(String authorization, Long targetNo) {
        User myInfo = null;
        try {
            Map user = tokenManager.decryptToken(authorization, Const.salt);
            myInfo = userDao.addFollowing((Long)user.get("userNo"));
            User target = userDao.addFollowing(targetNo);
            myInfo.getFollowing().add(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return myInfo;
    }
}
