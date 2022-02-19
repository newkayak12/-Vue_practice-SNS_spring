package com.vue.vue_practicesns_backend.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDto implements Serializable {
    private final Long imageNo;
    private final String imagePath;
    private final String imageName;
}
