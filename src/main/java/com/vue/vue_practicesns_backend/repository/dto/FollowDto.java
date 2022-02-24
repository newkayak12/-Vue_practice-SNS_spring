package com.vue.vue_practicesns_backend.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowDto implements Serializable {
    private Long id;
    private LocalDateTime regDate;
    private UserDto fromNo;
    private UserDto toNo;
}
