package com.vue.vue_practicesns_backend.repository.entity.follow;

import com.vue.vue_practicesns_backend.repository.entity.base.RegistratedDate;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow extends RegistratedDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn
    private User fromNo;

    @ManyToOne
    @JoinColumn
    private User toNo;

}
