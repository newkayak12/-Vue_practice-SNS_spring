package com.vue.vue_practicesns_backend.repository.entity.user;

import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
import com.vue.vue_practicesns_backend.repository.entity.image.Image;
import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column(length = 50)
    private String userId;
    @Column(columnDefinition = "varchar(200)")
    private String password;
    @Column(length = 20)
    private String userName;

    @OneToOne
    @JoinColumn(name = "profileImageNo")
    private Image profileImage;

    @OneToOne
    @JoinColumn(name = "backgroundImageNo")
    private Image backgroundImage;

    @OneToMany (mappedBy = "userFollower")
    private List<User> follower = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "")
    private User userFollower = this;

    @OneToMany(mappedBy = "userFollowing")
    private List<User> following = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private User userFollowing = this;

    @Column(length = 255)
    private String link;
    private LocalDate birth;

    @ManyToMany
    @JoinTable(name = "like",
            joinColumns =@JoinColumn(name = "userNo"),
            inverseJoinColumns = @JoinColumn(name = "postNo")
    )
    private List<Post> likedPost = new ArrayList<>();
}
