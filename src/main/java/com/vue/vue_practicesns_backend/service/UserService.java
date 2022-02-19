package com.vue.vue_practicesns_backend.service;

import com.vue.vue_practicesns_backend.common.auth.TokenManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private TokenManager tokenManager;

}
