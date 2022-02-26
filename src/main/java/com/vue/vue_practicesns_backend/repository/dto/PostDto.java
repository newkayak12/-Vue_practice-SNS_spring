package com.vue.vue_practicesns_backend.repository.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto implements Serializable {
    private  LocalDate createdDate;
    private  LocalDate modifiedDate;
    private  Long PostNo;
    private  UserDto user;
    private  Boolean isMain;
    private  String outerLink;
    private  String movieLink;
    private  String content;
    private  List<String> hashtag;
    private  List<UserDto> likedUser;
}
