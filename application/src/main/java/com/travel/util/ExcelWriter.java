package com.travel.util;

import com.travel.entity.ScenicSpot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelWriter {

    private static List<String> CELL_HEADS;

    {
        CELL_HEADS = new ArrayList<>();
        CELL_HEADS.add("景点从属");
        CELL_HEADS.add("景点名称");
    }


    public static void writeToFile(Workbook workbook, String desPath){
        FileOutputStream fileOutputStream = null;
        try {
            File exportFile = new File(desPath);
            if (!exportFile.exists()){
            }
            exportFile.createNewFile();
            fileOutputStream = new FileOutputStream(desPath);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Workbook exportData(List<ScenicSpot> dataList){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = buildDataSheet(workbook);
        int rowNum = 1;
        for (Iterator<ScenicSpot> it = dataList.iterator(); it.hasNext(); ){
            ScenicSpot data = it.next();
            if (data == null){
                continue;
            }
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(data,row);
        }

        return workbook;
    }

    private static void convertDataToRow(ScenicSpot data , Row row){
        int cellNum = 0;
        Cell cell;
        //从属
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSubordinate() == null ? "" : data.getSubordinate());
        //景区名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getName() == null ? "" : data.getName());

    }

    private static Sheet buildDataSheet(Workbook workbook){
        //设置标题行
        Sheet sheet = workbook.createSheet();
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
        }
        return sheet;
    }
}
