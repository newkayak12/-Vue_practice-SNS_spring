package com.vue.vue_practicesns_backend.repository;

import com.vue.vue_practicesns_backend.common.Const;
import com.vue.vue_practicesns_backend.repository.dto.ImageDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileUpload {
    public ImageDto fileUpload(MultipartFile file){
        ImageDto dto = new ImageDto();
        String filePath = Const.filePath;
        Map[] resolution = new Map[3];
        Map res1080 = new HashMap();
        res1080.put("width", 1920);
        res1080.put("height", 1080);
        Map res720 = new HashMap();
        res720.put("width",1280);
        res720.put("height", 720);
        Map res480 = new HashMap();
        res480.put("width", 852);
        res480.put("height", 480);
        resolution[0] = res1080;
        resolution[1] = res720;
        resolution[2] = res480;

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMDD")).toString();
        String time = String.valueOf(System.currentTimeMillis());
        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.indexOf("."));
        Integer salt = Integer.valueOf((int)Math.random()*1000);
        String storePath = date+"/"+time+"_"+salt+extension;

        String originalPath =  filePath+"/origin/"+storePath;
        try {
            file.transferTo(new File(originalPath));
            BufferedImage originalImage = ImageIO.read(new File(originalPath));
            int type = originalImage.getType() == 0?  BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            Arrays.stream(resolution).forEach(v->{
                BufferedImage resizeImage = resizeImage(originalImage, type, (int)v.get("width"), (int)v.get("height"));
                try {
                    ImageIO.write(resizeImage, extension, new File((filePath+"/"+(int)v.get("heigth")+"/")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        dto.setImageName(file.getOriginalFilename());
        dto.setImagePath(storePath);
        return dto;
    }

    public static BufferedImage resizeImage (BufferedImage originalImage , int type, int width, int height){
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();;
        return resizedImage;
    }
}
