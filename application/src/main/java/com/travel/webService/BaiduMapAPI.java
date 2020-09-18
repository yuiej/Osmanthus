package com.travel.webService;

import com.travel.entity.ScenicSpot;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class BaiduMapAPI {

    private static final String AK = "4fpD025jSl7icdiwcX5Uk73GWyGFhu2l";

    /**
     * http://api.map.baidu.com/geocoding/v3/?address=北京市海淀区上地十街10号&output=json&ak=您的ak&callback=showLocation //GET请求
     */
    private static final String BASE_URL_GEO_CODING = "http://api.map.baidu.com/geocoding/v3/?";
    private static final int STATUS_SUCCESS = 0;


    public static void getCoordinate(ScenicSpot spot) {
        String address = spot.getName();
        if (StringUtils.isEmpty(address)){
            return;
        }
        Map<String,String> param = new HashMap<>();
        param.put("address",spot.getName());
        //param.put("city",spot.getSubordinate());
        String json = loadJson(param);
        if (!StringUtils.isEmpty(json)){
            JSONObject object = new JSONObject(json);
            int status = object.getInt("status");
            if (STATUS_SUCCESS == status){
                String lng = object.getJSONObject("result").getJSONObject("location").get("lng").toString();//经度
                String lat = object.getJSONObject("result").getJSONObject("location").get("lat").toString();//纬度
                spot.setLongitude(lng);
                spot.setDimensionality(lat);
            }
        }
    }

    private static String getUri(Map<String,String> param){
        StringBuilder uri = new StringBuilder();
        uri.append(BASE_URL_GEO_CODING).append("ak=").append(AK).append("&output=json");
        for (String key: param.keySet()
             ) {
            uri.append("&").append(key).append("=").append(param.get(key));
        }
        return uri.toString();
    }

    

    public static String loadJson(Map<String,String> param){
        if (param == null){
            return null;
        }
        StringBuilder json = new StringBuilder();

        try {
            URL url = new URL(getUri(param));
            URLConnection urlConnection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream() , "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null){
                json.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
