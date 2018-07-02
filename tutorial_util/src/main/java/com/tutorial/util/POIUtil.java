package com.tutorial.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jimmy. 2018/7/2  11:40
 */
public class POIUtil {


    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File("D:\\data\\电视节目对应表.xlsx"));
        //读取全部sheet
        Integer sheetNum = workbook.getNumberOfSheets();
        System.out.println(sheetNum);
        //读取一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        //读取最后一行
        Integer rowNum = sheet.getLastRowNum();
        System.out.println(rowNum);
        Row row = null;
        for(int i=2;i<rowNum;i++){
            row = sheet.getRow(i);
            Short celNum = row.getLastCellNum();
            Cell cell = row.getCell(4);
            if(cell != null){
               System.out.println(row.getCell(1)+"--"+cell.getStringCellValue().replaceAll("(\r\n|\r|\n|\n\r)", ""));
            }
           // System.out.println(cell.getCellFormula());

          //  System.out.println("序号："+row.getCell(1)+"--频道名："+row.getCell(2)+"--channelcode："+row.getCell(3)+"--uuid"+row.getCell(4));
        }
    }
}
