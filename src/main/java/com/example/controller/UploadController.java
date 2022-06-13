package com.example.controller;

import com.example.common.utils.CommonUtils;
import com.example.common.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 22:05
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value(value="${joan.path.uploadType}")
    private String uploadType;
    @Value(value = "${joan.path.uploadPath}")
    private String uploadPath;

    @GetMapping("/uploadPic")
    public Result<?> uploadPic(MultipartFile file){
        String s = CommonUtils.uploadLocal(file, "images", uploadPath);
        return Result.OK(s);
    }
}
