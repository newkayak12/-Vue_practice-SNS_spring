package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.repository.entity.ttest.Tests;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private TestDao testDao;

    public void insertTest(Tests test1) {

    }
}
