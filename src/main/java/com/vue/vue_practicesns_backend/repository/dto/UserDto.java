package com.vue.vue_practicesns_backend.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable{
    private  LocalDate createdDate;
    private  LocalDate modifiedDate;
    private  Long userNo;
    private  String userId;
    @JsonIgnore
    private  String password;
    private  String userName;
    private  String phone;
    private  ImageDto profileImage;
    private  ImageDto backgroundImage;
    private  String link;
    private  LocalDate birth;
    private  List<PostDto> likedPost;
    @JsonIgnoreProperties({"id","regDate","toNo"})
    private  List<FollowDto> follower;
    @JsonIgnoreProperties({"id","regDate","fromNo"})
    private  List<FollowDto> following;
}
