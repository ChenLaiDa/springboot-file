package com.example.controller;


import com.example.common.vo.Result;
import com.example.model.entity.CourseEntity;
import com.example.model.entity.ExcelEntity;
import com.example.model.entity.StudentEntity;
import com.example.model.entity.TeacherEntity;
import com.example.service.ExcelService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 19:58
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Resource
    private ExcelService excelService;

    @Value(value = "${joan.path.uploadPath}")
    private String uploadPath;
    /**
     * 非注解
     */
    @GetMapping("/export")
    public Result<?> export(HttpServletRequest request, HttpServletResponse response){
        Workbook workbook = excelService.exportExcel();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\excelTest.xls");
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.OK();


    }

    /**
     * 注解 - 对象定义
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/exportXls")
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response) {
        String title = "导出excel测试";
        //获取导出数据
        List<ExcelEntity> exportList = new ArrayList<>();
        ExcelEntity excelEntity = new ExcelEntity();
        excelEntity.setId("1");
        excelEntity.setName("张三");
        excelEntity.setAge(18);
        try {
            excelEntity.setBirthday(DateUtils.parseDate("1998-06-01", "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        excelEntity.setPhone("123456789");

        excelEntity.setCreateTime(new Date());
        excelEntity.setLogo("images/wallhaven-wywppq_1651415352604.jpg");
        exportList.add(excelEntity);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, "测试"), ExcelEntity.class, exportList);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\excelTest.xls");
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.OK();
    }

    /**
     * 注解  - 集合定义
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/listExportXls")
    public Result<?> listExportXls(HttpServletResponse response,HttpServletRequest request) {
        //导出数据
        List<CourseEntity> exportList = Lists.newArrayList();
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId("1");
        courseEntity.setName("语文");

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId("1");
        teacherEntity.setName("张三");
        courseEntity.setTeacherEntity(teacherEntity);

        List<StudentEntity> studentEntityList = Lists.newArrayList();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId("1");
        studentEntity.setSex(1);
        studentEntity.setName("陈冲");
        studentEntity.setRegistrationDate(new Date());
        try {
            studentEntity.setBirthday(DateUtils.parseDate("1998-06-01", "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        studentEntityList.add(studentEntity);
        StudentEntity studentEntity02 = new StudentEntity();
        studentEntity02.setId("2");
        studentEntity02.setSex(2);
        studentEntity02.setName("王慧");
        studentEntity02.setRegistrationDate(new Date());
        try {
            studentEntity02.setBirthday(DateUtils.parseDate("1999-11-05", "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        studentEntityList.add(studentEntity02);
        courseEntity.setStudentEntityList(studentEntityList);
        exportList.add(courseEntity);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("课程信息", "课程信息"), CourseEntity.class, exportList);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\excelTest.xls");
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Result.OK();
    }
}
