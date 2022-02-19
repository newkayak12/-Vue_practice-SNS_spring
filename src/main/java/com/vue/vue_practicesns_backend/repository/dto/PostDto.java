package com.vue.vue_practicesns_backend.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class PostDto implements Serializable {
    private final LocalDate createdDate;
    private final LocalDate modifiedDate;
    private final Long PostNo;
    private final UserDto user;
    private final Boolean isMain;
    private final String outerLink;
    private final String movieLink;
    private final String content;
    private final List<ImageDto> imageList;
    private final List<String> hashtag;
    private final List<UserDto> likedUser;
}
