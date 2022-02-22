package com.vue.vue_practicesns_backend.repository.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable{
    private  LocalDate createdDate;
    private  LocalDate modifiedDate;
    private  Long userNo;
    private  String userId;
    private  String password;
    private  String userName;
    private  String phone;
    private  ImageDto profileImage;
    private  ImageDto backgroundImage;
    private  String link;
    private  LocalDate birth;
    private  List<PostDto> likedPost;
    private  List<FollowDto> follower;
    private  List<FollowDto> following;
}
