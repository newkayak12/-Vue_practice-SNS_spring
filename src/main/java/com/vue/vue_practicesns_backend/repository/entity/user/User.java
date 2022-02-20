package com.vue.vue_practicesns_backend.repository.entity.user;

import com.vue.vue_practicesns_backend.common.exceptions.DuplicateException;
import com.vue.vue_practicesns_backend.common.exceptions.NoSuchElementException;
import com.vue.vue_practicesns_backend.repository.entity.base.BaseEntity;
import com.vue.vue_practicesns_backend.repository.entity.follow.Follow;
import com.vue.vue_practicesns_backend.repository.entity.image.Image;
import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode
public class User extends BaseEntity  {
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

    public void addFollow(User user) throws DuplicateException {
        for(Follow follow : this.getFollowing()){
            if(follow.getFromNo().equals(this) && follow.getToNo().equals(user)){
                throw new DuplicateException("이미 팔로우한 사용자입니다.");
            }
        }

        Follow follow = Follow.builder().fromNo(this).toNo(user).build();
        this.getFollowing().add(follow);
    }
    public void deleteFollow(User user) throws NoSuchElementException {
        this.getFollowing().stream().forEach(v->{
            if(v.getFromNo().equals(this) && v.getToNo().equals(user)){
                this.getFollowing().remove(user);
            }
        });
        throw  new NoSuchElementException("해당 사용자는 존재하지 않습니다.");
    }
}
