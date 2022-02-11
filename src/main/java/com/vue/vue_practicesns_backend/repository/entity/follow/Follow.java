package com.vue.vue_practicesns_backend.repository.entity.follow;

import com.vue.vue_practicesns_backend.repository.entity.base.RegistratedDate;
import com.vue.vue_practicesns_backend.repository.entity.compositKey.FollowId;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Follow")
public class Follow extends RegistratedDate {

    @EmbeddedId
    private FollowId followId;

}
