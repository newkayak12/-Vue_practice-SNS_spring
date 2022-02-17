package com.vue.vue_practicesns_backend.repository.entity.ttest;

import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
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
@IdClass(Test_TestManyToManyIdClass.class)
public class Test_TestManyToMany  {

    @Id
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Tests tests;
    @Id
    @ManyToOne
    @JoinColumn(name = "test_manytomany_id")
    private TestManyToMany testManyToMany;

//    @ElementCollection
//    @CollectionTable(name = "CollectionTable", joinColumns = @JoinColumn(name = "String_id"))
//    private List<String> stringList = new ArrayList<>();
}
