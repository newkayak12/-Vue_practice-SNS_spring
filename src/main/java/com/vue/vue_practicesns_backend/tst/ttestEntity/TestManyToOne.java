package com.vue.vue_practicesns_backend.repository.entity.ttest;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestManyToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value3;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Tests test;
}
