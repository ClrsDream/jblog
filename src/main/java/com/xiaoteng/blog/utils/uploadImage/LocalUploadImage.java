package com.xiaoteng.blog.utils.uploadImage;

import com.xiaoteng.blog.config.UploadImageConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

public class LocalUploadImage implements UploadImage {

    private UploadImageConfig uploadImageConfig;

    public LocalUploadImage(UploadImageConfig uploadImageConfig) {
        this.uploadImageConfig = uploadImageConfig;
    }

    @Override
    public String upload(MultipartFile file) {
        // 生成路径
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        LocalDate localDate = LocalDate.now();
        String year = localDate.getYear() + "";
        String month = localDate.getMonth() + "";
        String day = localDate.getDayOfMonth() + "";
        Path path = Paths.get(uploadImageConfig.getStorage(), year, month, day);
        String savePath = path.toAbsolutePath().toString();
        File file1 = new File(savePath);
        file1.mkdirs();
        String uuid = UUID.randomUUID().toString() + suffix;
        File realFile = new File(file1, uuid);
        try {
            file.transferTo(realFile);
            return realFile.getAbsolutePath().replace(uploadImageConfig.getStorage(), "");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
