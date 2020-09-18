package com.travel.util;

import java.io.File;

import com.travel.entity.ScenicSpot;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressUtil {

    public static Workbook getWorkbook(String excelPath) throws IOException, InvalidFormatException{
        File excel = new File(excelPath);
        if (excel.isFile() && excel.exists()) {
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
            //根据文件后缀（xls/xlsx）进行判断
            if ( "xls".equals(split[1])){
                FileInputStream fis = new FileInputStream(excel);   //文件流对象
                return new HSSFWorkbook(fis);
            } else if ("xlsx".equals(split[1])){
                return new XSSFWorkbook(excel);
            } else {
                System.out.println("文件类型错误!");
            }
        } else {
            System.out.println("找不到指定的文件");
        }
        return null;
    }

    public static void readDataFromXls() throws IOException, InvalidFormatException{
        //excel文件路径
        String excelPath = "D:\\yujie\\Documents\\旅游地点.xlsx";

        Workbook wb = getWorkbook(excelPath);
        //开始解析
        Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
        int firstRowIndex = sheet.getFirstRowNum();   //第一行是列名，所以不读
        int lastRowIndex = sheet.getLastRowNum();
        System.out.println("firstRowIndex: "+firstRowIndex);
        System.out.println("lastRowIndex: "+lastRowIndex);

        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
            System.out.println("rIndex: " + rIndex);
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                    Cell cell = row.getCell(cIndex);
                    if (cell != null) {
                        System.out.println(cell.toString());
                    }
                }
            }
        }
    }


    public static void splitSpotTofiles() throws IOException, InvalidFormatException{
        //excel文件路径
        String excelPath = "D:\\yujie\\Documents\\旅游地点.xlsx";

        Workbook wb = getWorkbook(excelPath);
        //开始解析
        List<ScenicSpot> spotList = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(1);     //读取sheet 0
        for(int rIndex = sheet.getFirstRowNum(); rIndex <= sheet.getLastRowNum(); rIndex++) {   //遍历行
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                Cell cell = row.getCell(0);
                String all = cell.toString();
                String[] split = all.split("：");

                for (String subSpot:split[1].split("、")
                     ) {
                    ScenicSpot scenicSpot = new ScenicSpot();
                    scenicSpot.setSubordinate(split[0].trim());
                    scenicSpot.setName(subSpot.trim());
                    spotList.add(scenicSpot);
                }
            }
        }

        String des = "D:\\yujie\\Documents\\休闲年卡.xlsx";

        Workbook workbook = ExcelWriter.exportData(spotList);
        ExcelWriter.writeToFile(workbook,des);
    }

    private AddressUtil(){

    }
}
