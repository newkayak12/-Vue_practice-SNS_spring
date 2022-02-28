package com.vue.vue_practicesns_backend.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
public class FollowDto implements Serializable {
    private Long id;
    private LocalDateTime regDate;
    @JsonIgnoreProperties({"follower", "following", "password", "likedPost"})
    private UserDto fromNo;
    @JsonIgnoreProperties({"follower", "following", "password", "likedPost"})
    private UserDto toNo;
}
