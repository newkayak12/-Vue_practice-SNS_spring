package com.vue.vue_practicesns_backend.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto implements Serializable {
    private  LocalDate createdDate;
    private  LocalDate modifiedDate;
    private  Long PostNo;
    @JsonIgnoreProperties({"password", "likedPost", "follower", "following"})
    private  UserDto user;
    private  Boolean isMain;
    private  String outerLink;
    private  String movieLink;
    private  String content;
    private  List<String> hashtag;
    @JsonIgnoreProperties({"createdDate", "modifiedDate", "userId", "password", "userName", "phone", "profileImage", "backgroundImage", "link","birth","likedPost", "follower", "following"})
    private  List<UserDto> likedUser;
}
