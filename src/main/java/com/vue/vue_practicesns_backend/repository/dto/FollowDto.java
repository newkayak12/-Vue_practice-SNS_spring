package com.vue.vue_practicesns_backend.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FollowDto implements Serializable {
    private final LocalDateTime regDate;
    private final Long id;
    private final UserDto fromNo;
    private final UserDto toNo;
}
