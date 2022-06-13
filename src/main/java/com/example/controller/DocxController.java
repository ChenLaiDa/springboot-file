package com.example.controller;



import com.example.common.vo.Result;
import freemarker.template.SimpleDate;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/5/2 15:01
 */
@Slf4j
@RestController
@RequestMapping("/docx")
public class DocxController {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * poi导出word模板
     *
     * @return
     */
    @GetMapping("/docxTemplateExport")
    public Result<?> docxTemplateExport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("company", "深圳市腾讯计算机系统有限公司");
        map.put("ele", "200.00");
        map.put("water", "100.38");
        map.put("totalFeet", "300.38");

        FileOutputStream fileOutputStream = null;
        File docx = null;
        try {
            XWPFDocument xwpfDocument = WordExportUtil.exportWord07("/templates/notice.docx", map);
            //创建临时文件
            //临时文件保存路径
            String path = "D://";
            String yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            docx = new File(path + yyyyMMddHHmmss + ".docx");
            fileOutputStream = new FileOutputStream(docx);
            xwpfDocument.write(fileOutputStream);
            fileOutputStream.flush();

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "inline;filename="+System.currentTimeMillis()+".docx");
            FileCopyUtils.copy(new FileInputStream(docx), response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(docx != null){
                docx.delete();
            }

        }
        return Result.OK();
    }

    /**
     * freemarker导出word模板
     *
     * @return
     */
    @GetMapping("/freemarkerTemplateExport")
    public Result<?> freemarkerTemplateExport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("company", "深圳市腾讯计算机系统有限公司");
        map.put("ele", "200.00");
        map.put("water", "100.38");
        map.put("totalFee", "300.38");

        //生成doc
        Writer out = null;
        InputStream fis = null;
        BufferedOutputStream toClient = null;
        File outFile = null;
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("水电费缴费通知书.ftl");
            //临时文件保存路径
            String path = "D://";
            log.info("path:" + path);
            String yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //临时文件
            outFile = new File(String.format("%s/%s.%s", path, yyyyMMddHHmmss, "doc"));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(map, out);
            out.flush();

            //读取临时文件,传给前端
            fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (toClient != null) {
                    toClient.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //删除临时文件
            outFile.delete();

        }
        return Result.OK();
    }

}
