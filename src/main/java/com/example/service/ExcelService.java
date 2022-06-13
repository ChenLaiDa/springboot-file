package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.entity.ExcelEntity;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 21:14
 */
public interface ExcelService extends IService<ExcelEntity> {

    Workbook exportExcel();
}
