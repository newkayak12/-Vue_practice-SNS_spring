package com.vue.vue_practicesns_backend.repository.entity.compositKey;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class FollowId  implements Serializable{

    private Long followingNo;
    private Long followerNo;

}
