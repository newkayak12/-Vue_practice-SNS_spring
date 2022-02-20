package com.vue.vue_practicesns_backend.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Const {
    public static String salt = "";
    public static String filePath = "";

//  Setter
    @Value("${static.salt}")
    public  void setSalt(String _salt){
        this.salt = _salt;
    }
    @Value("${static.filePath}")
    public void setFilePath(String _filePath){
        this.filePath = _filePath;
    }



}
