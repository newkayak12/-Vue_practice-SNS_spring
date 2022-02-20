package com.vue.vue_practicesns_backend.repository.entity.user;

import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
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
    @Column(length = 20)
    private String phone;
    @OneToOne
    @JoinColumn(name = "profile_image_no")
    private Image profileImage;
    @OneToOne
    @JoinColumn(name = "background_image_no")
    private Image backgroundImage;
    @Column(length = 255)
    private String link;
    private LocalDate birth;
    @ManyToMany(mappedBy = "likedUser", fetch = FetchType.EAGER)
    private List<Post> likedPost = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "Follow",
            joinColumns = @JoinColumn(name = "fromNo"),
            inverseJoinColumns = @JoinColumn(name = "toNo")
    )
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "Follow",
            joinColumns = @JoinColumn(name = "toNo"),
            inverseJoinColumns = @JoinColumn(name = "fromNo")
    )
    private List<Follow> following = new ArrayList<>();


}
