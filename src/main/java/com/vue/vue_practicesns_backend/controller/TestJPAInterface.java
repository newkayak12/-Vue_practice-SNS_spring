package com.vue.vue_practicesns_backend.controller;

import com.vue.vue_practicesns_backend.repository.entity.ttest.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJPAInterface extends JpaRepository<Tests, Long> {
    @Override
    <S extends Tests> S saveAndFlush(S entity);

    @Override
    <S extends Tests> S save(S entity);
}
