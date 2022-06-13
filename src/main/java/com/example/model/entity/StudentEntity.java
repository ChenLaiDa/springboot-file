package com.example.model.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 20:52
 */
@Data
public class StudentEntity {
    /**
     * id
     */
    private String id;
    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名", height = 20, width = 30)
    private String name;
    /**
     * 学生性别
     */
    @Excel(name = "学生性别", replace = {"男_1", "女_2"}, suffix = "生")
    private int sex;

    @Excel(name = "出生日期", format = "yyyy-MM-dd", width = 20)
    private Date birthday;

    @Excel(name = "进校日期", format = "yyyy-MM-dd")
    private Date registrationDate;
}
