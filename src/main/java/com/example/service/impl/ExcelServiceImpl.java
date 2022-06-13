package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.mapper.ExcelEntityMapper;
import com.example.model.entity.ExcelEntity;
import com.example.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/1 21:14
 */
@Service
public class ExcelServiceImpl  extends ServiceImpl<ExcelEntityMapper, ExcelEntity> implements ExcelService {
    @Override
    public Workbook exportExcel() {
        //导出数据
        List<ExcelEntity> exportList = new ArrayList<>();
        ExcelEntity excelEntity = new ExcelEntity();
        excelEntity.setId("1");
        excelEntity.setName("陈冲");
        excelEntity.setAge(18);
        exportList.add(excelEntity);
        ExcelEntity excelEntity1 = new ExcelEntity();
        excelEntity1.setId("2");
        excelEntity1.setName("王慧");
        excelEntity1.setAge(19);
        exportList.add(excelEntity1);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("test");
        //首行
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("序号");
        firstRow.createCell(1).setCellValue("姓名");
        firstRow.createCell(2).setCellValue("年龄");
        for (int i = 0; i < exportList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(exportList.get(i).getId() == null ? "": exportList.get(i).getId());
            row.createCell(1).setCellValue(exportList.get(i).getName() == null ? "": exportList.get(i).getName());
            row.createCell(2).setCellValue(exportList.get(i).getAge() == null ? "": exportList.get(i).getAge().toString());
        }
        return workbook;



    }
}
