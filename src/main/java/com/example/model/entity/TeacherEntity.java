package com.example.model.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 20:52
 */
@Data
public class TeacherEntity {
    private String id;
    /** name */
    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1",needMerge = true)
    private String name;
}
