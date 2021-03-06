package com.vue.vue_practicesns_backend.repository.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class RegistratedDate {

    @CreatedDate
    private LocalDateTime regDate;
}
