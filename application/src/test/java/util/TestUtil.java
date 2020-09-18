package util;

import com.travel.entity.ScenicSpot;
import com.travel.generator.ExcelGenerator;
import com.travel.util.AddressUtil;
import com.travel.util.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestUtil {

    @Test
    public void testAddress() throws IOException, InvalidFormatException{
        //excel文件路径
        String excelPath = "D:\\yujie\\Documents\\休闲年卡.xlsx";
        //AddressUtil.readDataFromXls();
        AddressUtil.splitSpotTofiles();

        List<ScenicSpot> spotList = ExcelReader.readExcel(excelPath);
        spotList.stream().forEach(scenicSpot -> {
            System.out.println(scenicSpot.toString());
        });
    }

    @Test
    public void testAPI() throws IOException, JSONException{
        String name = "苏州盘门景区";
        //String s = BaiduMapAPI.loadJson(name);
        String jsons = "{\"status\":0,\"result\":{\"location\":{\"lng\":120.62346063242625,\"lat\":31.294803388697276},\"precise\":0,\"confidence\":50,\"comprehension\":100,\"level\":\"旅游景点\"}}";
        JSONObject object = new JSONObject(jsons);

        String status = object.get("status").toString();
        String lng = object.getJSONObject("result").getJSONObject("location").get("lng").toString();
        String lat = object.getJSONObject("result").getJSONObject("location").get("lat").toString();
        System.out.println(status);
        System.out.println(lng);
        System.out.println(lat);
    }

    @Test
    public void testLast(){
        String source = "D:\\yujie\\Documents\\休闲年卡.xlsx";
        String des = "D:\\yujie\\Documents\\休闲年卡景点-经纬度.xlsx";
        ExcelGenerator.generateScenicSpotsCoordinate(source,des);
    }


}
