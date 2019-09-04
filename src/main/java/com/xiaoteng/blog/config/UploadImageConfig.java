package com.xiaoteng.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ConfigurationProperties(prefix = "blog.upload.image")
public class UploadImageConfig {

    private String storage;

    private double size;

    private ArrayList<String> mimes = new ArrayList<>();

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public ArrayList<String> getMimes() {
        return mimes;
    }

    public void setMimes(ArrayList<String> mimes) {
        this.mimes = mimes;
    }

}
