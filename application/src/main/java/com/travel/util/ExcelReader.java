package com.travel.util;

import com.travel.entity.ScenicSpot;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Workbook getWorkbook(String fileType, InputStream inputStream) throws IOException{
        Workbook workbook = null;
        if (XLS.equalsIgnoreCase(fileType)){
            workbook = new HSSFWorkbook(inputStream);
        }else if (XLSX.equalsIgnoreCase(fileType)){
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    public static List<ScenicSpot> readExcel(String excelPath){
        Workbook wb = null;
        FileInputStream inputStream = null;

        try {
            String fileType = excelPath.substring(excelPath.lastIndexOf(".") + 1);
            File excel = new File(excelPath);
            if (!excel.exists()){
                System.out.println("文件不存在！");
                return null;
            }
            //获取工作簿
            inputStream = new FileInputStream(excel);
            wb = getWorkbook(fileType,inputStream);
            //读取excel中的数据
            return parseExcel(wb);
        } catch (Exception e) {
            System.out.println("解析Excel失败，文件名：" + excelPath + " 错误信息：" + e.getMessage());
            return null;
        } finally {
                try {
                    if (null != wb) {
                        wb.close();
                    }
                    if (null != inputStream){
                        inputStream.close();
                    }
                } catch (Exception e) {
                    System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
                }
        }
    }

    private static List<ScenicSpot> parseExcel(Workbook workbook){
        List<ScenicSpot> resultList = new ArrayList<>();
        //开始解析
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null){
                continue;
            }
            int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();

            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                Row row = sheet.getRow(rIndex);
                if (row == null) {
                    continue;
                }

                ScenicSpot scenicSpot = convertRowToData(row);
                /*if (scenicSpot == null){
                    continue;
                }*/
                resultList.add(scenicSpot);
            }


        }

        return resultList;
    }

    private static ScenicSpot convertRowToData(Row row){
        ScenicSpot scenicSpot = new ScenicSpot();
        Cell cell;

        int nameCellNum = 0;
        //subordinate
        cell = row.getCell(nameCellNum);
        scenicSpot.setSubordinate(convertCellToString(cell));
        //name
        cell = row.getCell(nameCellNum);
        scenicSpot.setName(convertCellToString(cell));

        return scenicSpot;
    }

    private static String convertCellToString(Cell cell){
        return cell == null ? null : cell.toString();
    }


    /**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     *private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }*/
}
