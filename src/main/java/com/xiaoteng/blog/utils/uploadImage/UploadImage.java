package com.xiaoteng.blog.utils.uploadImage;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImage {
    public String upload(MultipartFile file);
}
