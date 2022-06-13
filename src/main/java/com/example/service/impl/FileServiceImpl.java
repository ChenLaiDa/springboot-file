package com.example.service.impl;


import com.example.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 17:01
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            //1.创建FileInputStream对象,指定文件路径
            FileInputStream fileInputStream = new FileInputStream(fileName);
            //2,创建一个缓冲区
            byte[] bytes = new byte[1024];
            //3.通过FileInputStream对象,读取文件内容
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public String readFileByFileReader(String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return sb.toString();

    }

    @Override
    public void writeFile(String fileName, String content) {
        try {
            //1.创建FileOutputStream对象,指定文件路径,append:追加
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFileByFileWriter(String fileName, String content, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, append);
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void copyFile(String origin, String dest) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(origin);
            fileOutputStream = new FileOutputStream(dest);
            StringBuilder stringBuilder = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                stringBuilder.append(new String(bytes, 0, len));
            }
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
