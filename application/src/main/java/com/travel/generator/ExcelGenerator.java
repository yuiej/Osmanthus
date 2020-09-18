package com.travel.generator;

import com.travel.entity.ScenicSpot;
import com.travel.util.ExcelReader;
import com.travel.util.ExcelWriter;
import com.travel.webService.BaiduMapAPI;

import java.util.List;

public class ExcelGenerator {

    public static void generateScenicSpotsCoordinate(String source,String des){
        List<ScenicSpot> spotList = ExcelReader.readExcel(source);
        spotList.forEach(spot -> {
            BaiduMapAPI.getCoordinate(spot);
        });
        ExcelWriter.writeToFile(ExcelWriter.exportData(spotList),des);
    }
}
