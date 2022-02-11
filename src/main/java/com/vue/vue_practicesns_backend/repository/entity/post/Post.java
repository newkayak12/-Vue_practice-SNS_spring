package com.vue.vue_practicesns_backend.repository.entity.post;

import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
import com.vue.vue_practicesns_backend.repository.entity.image.Image;
import com.vue.vue_practicesns_backend.repository.entity.user.User;
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
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long PostNo;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;

    @Column
    private Boolean isMain;
    private String postLink;

    @Column(columnDefinition = "longText")
    private String content;

    @ManyToMany
    @JoinTable(name = "PostImage",
            joinColumns = @JoinColumn(name = "postNo"),
            inverseJoinColumns = @JoinColumn(name = "imageNo")
    )
    List<Image> imageList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "hashtag",
            joinColumns = @JoinColumn(name = "postNo")
    )
    List<String> hashtag = new ArrayList<>();

    @ManyToMany(mappedBy = "likedPost")
    List<User> userList = new ArrayList<>();
}
