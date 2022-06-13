package com.example.service;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 17:01
 */
public interface FileService {
    //读取文件
    String readFile(String fileName);
    String readFileByFileReader(String fileName);

    //写入文件
    void writeFile(String fileName, String content);
    void writeFileByFileWriter(String fileName, String content, boolean append);

    //复制文件
    void copyFile(String fileName, String newFileName);
}
