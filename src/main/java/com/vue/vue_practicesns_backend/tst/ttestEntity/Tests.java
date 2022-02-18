package com.vue.vue_practicesns_backend.repository.entity.ttest;

import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tests extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date date;

    @OneToMany(mappedBy = "test")
    private List<TestManyToOne> testManyToOneList = new ArrayList<>();

    @OneToMany(mappedBy = "tests")
    private List<Test_TestManyToMany> testManyToManyList = new ArrayList<>();
}
