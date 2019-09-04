package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.config.UploadImageConfig;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.utils.uploadImage.LocalUploadImage;
import com.xiaoteng.blog.utils.uploadImage.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class UploadController {

    @Autowired
    private UploadImageConfig uploadImageConfig;

    @ResponseBody
    @PostMapping(WebRouter.UPLOAD_IMAGE)
    public UploadImageResponse uploadImage(@RequestParam("file") MultipartFile file) {
        UploadImageResponse uploadImageResponse = new UploadImageResponse(0);
        // 判断图片大小
        double size = Math.ceil(file.getSize() / 1024);
        if (uploadImageConfig.getSize() < size) {
            // 超过预设大小
            uploadImageResponse.setErrno(101);
            return uploadImageResponse;
        }
        // 判断格式
        if (!uploadImageConfig.getMimes().contains(file.getContentType())) {
            uploadImageResponse.setErrno(102);
            return uploadImageResponse;
        }
        UploadImage uploadImage = new LocalUploadImage(uploadImageConfig);
        String path = uploadImage.upload(file);
        if (path.isEmpty()) {
            uploadImageResponse.setErrno(103);
            return uploadImageResponse;
        }
        // 上传成功
        uploadImageResponse.getData().add(path);
        return uploadImageResponse;
    }

    private class UploadImageResponse {
        private int errno;
        private ArrayList<String> data;

        public UploadImageResponse(int code) {
            this.errno = code;
            this.data = new ArrayList<>();
        }

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public ArrayList<String> getData() {
            return data;
        }

        public void setData(ArrayList<String> data) {
            this.data = data;
        }
    }

}
