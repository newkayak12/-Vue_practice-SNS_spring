package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.common.Const;
import com.vue.vue_practicesns_backend.repository.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileUpload {
    public ImageDto fileUpload(MultipartFile file){
        ImageDto dto = new ImageDto();
        String filePath = Const.filePath;
        String[] resolution = {"origin", "1080", "720", "480"};
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD")).toString();
        String time = String.valueOf(System.currentTimeMillis());
        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.indexOf("."));
        Integer salt = Integer.valueOf((int)Math.random()*1000);
        String storePath = date+"/"+time+"_"+salt+extension;
        Arrays.stream(resolution).forEach(v->{
            try {
                file.transferTo(new File(filePath+"/"+v+"/"+storePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        dto.setImageName(file.getOriginalFilename());
        dto.setImagePath(storePath);
        return dto;
    }
}
