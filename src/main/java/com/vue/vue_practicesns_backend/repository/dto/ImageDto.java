package com.vue.vue_practicesns_backend.repository.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ImageDto implements Serializable {
    private Long imageNo;
    private String imagePath;
    private String imageName;
}
