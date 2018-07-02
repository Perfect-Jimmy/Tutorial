package com.tutorial.controller;

import com.tutorial.domain.BDSSCode;
import com.tutorial.service.impl.BDSSCodeService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Jimmy. 2018/7/2  15:41
 */
@RestController
public class BDCode {
    @Autowired
    private BDSSCodeService bdssCodeService;

    @RequestMapping("codeInit")
    public String codeInit() throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File("D:\\data\\电视节目对应表.xlsx"));
        //读取全部sheet
        Integer sheetNum = workbook.getNumberOfSheets();
        System.out.println(sheetNum);
        //读取一个sheet
        Sheet sheet = workbook.getSheetAt(0);
       // System.out.println(sheet);
        //读取最后一行
        Integer rowNum = sheet.getLastRowNum();
        System.out.println(rowNum);
        Row row = null;

        for(int i=2;i<rowNum;i++){
            row = sheet.getRow(i);
            if(row.getCell(1) != null){
                //搜索code
                String ssCode = row.getCell(3).getStringCellValue();
                String[] array = ssCode.split("&");
                for(int j=0;j<array.length;j++){
                    BDSSCode bdssCode = new BDSSCode();
                    //搜索code
                    bdssCode.setSsCode(array[j]);
                    if(bdssCodeService.findBySsCode(array[j]) == null){
                        //频道名
                        bdssCode.setChannelName(row.getCell(2).getStringCellValue());
                        //百度code
                        String bdCode = row.getCell(4).getStringCellValue();
                        bdssCode.setBdCode(bdCode.trim().replaceAll(" ","").replaceAll("(\r\n|\r|\n|\n\r)", ""));
                        //创建时间
                        bdssCode.setCreateTime(new Date());
                        //更新时间
                        bdssCode.setUpdateTime(new Date());
                        bdssCodeService.saveOrUpdate(bdssCode);
                    }
                }
            }
        }
        return "success";
    }

}
