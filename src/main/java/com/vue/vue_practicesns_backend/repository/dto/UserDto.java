package com.vue.vue_practicesns_backend.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private final LocalDate createdDate;
    private final LocalDate modifiedDate;
    private final Long userNo;
    private final String userId;
    private final String password;
    private final String userName;
    private final ImageDto profileImage;
    private final ImageDto backgroundImage;
    private final String link;
    private final LocalDate birth;
    private final List<PostDto> likedPost;
    private final List<FollowDto> follower;
    private final List<FollowDto> following;
}
