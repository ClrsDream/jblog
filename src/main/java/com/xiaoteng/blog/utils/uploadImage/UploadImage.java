package com.xiaoteng.blog.utils.uploadImage;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImage {
    String upload(MultipartFile file);
}
