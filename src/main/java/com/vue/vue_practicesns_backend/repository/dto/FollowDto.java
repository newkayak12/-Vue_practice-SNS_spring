package com.vue.vue_practicesns_backend.repository.dto;

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
    private UserDto fromNo;
    private UserDto toNo;
}
