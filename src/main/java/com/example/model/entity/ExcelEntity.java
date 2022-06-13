package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 19:52
 */
@Data
@TableName("excel_entity")
public class ExcelEntity implements Serializable {
    @Excel(name = "序号", orderNum = "1")
    private String id;
    @Excel(name = "姓名", orderNum = "2")
    private String name;
    @Excel(name = "年龄", orderNum = "3")
    private Integer age;
    @Excel(name="手机",orderNum="4")
    private String phone;
    @Excel(name="生日",orderNum="5",format = "yyyy-MM-dd")
    private Date birthday;

    @Excel(name="创建时间",orderNum="6",format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "logo",orderNum = "7",type = 2,width = 40,height = 20)
    private String logo;

}
