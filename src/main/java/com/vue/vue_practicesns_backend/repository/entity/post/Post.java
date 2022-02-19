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
    @Column(columnDefinition = "varchar(500)")
    private String outerLink;
    @Column(columnDefinition = "varchar(500)")
    private String movieLink;
    @Column(columnDefinition = "longText")
    private String content;
    @ManyToMany(cascade = CascadeType.REMOVE)
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

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "likePost",
            joinColumns = @JoinColumn(name = "postNo"),
            inverseJoinColumns = @JoinColumn(name = "userNo")
    )
    List<User> likedUser = new ArrayList<>();
}
