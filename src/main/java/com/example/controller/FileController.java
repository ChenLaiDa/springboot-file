package com.example.controller;

import com.example.common.vo.Result;
import com.example.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 17:02
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @GetMapping("writeFile")
    public Result<?> writeFile(HttpServletRequest request, HttpServletResponse response){
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write("hello world".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return Result.OK();

    }

}
