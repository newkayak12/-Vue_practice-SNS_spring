package com.vue.vue_practicesns_backend.repository.entity.ttest;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestManyToMany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value1;
    private Integer value2;

    @OneToMany(mappedBy = "testManyToMany")
    private List<Test_TestManyToMany> testManyToManyList = new ArrayList<>();
}
