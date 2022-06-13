package com.example.model.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 20:51
 */
@Data
public class CourseEntity {


    private String id;
    @Excel(name = "课程名称", width = 15,orderNum = "1",needMerge = true)
    private String name;
    @ExcelEntity(id="absent")
    private TeacherEntity teacherEntity;
    @ExcelCollection(name="学生",orderNum = "3")
    private List<StudentEntity> studentEntityList;
}
