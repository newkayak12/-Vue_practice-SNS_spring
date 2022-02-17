package com.vue.vue_practicesns_backend.repository.entity.ttest;

import lombok.*;

import java.io.Serializable;
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test_TestManyToManyIdClass implements Serializable {
    private Tests tests;
    private TestManyToMany testManyToMany;
}
