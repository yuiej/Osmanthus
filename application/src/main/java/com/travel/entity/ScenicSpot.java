package com.travel.entity;

/**
 * 景点类
 */
public class ScenicSpot {

    private String name;
    private String address;
    private String longitude;//经度
    private String dimensionality;//纬度

    private String subordinate;//从属

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getDimensionality(){
        return dimensionality;
    }

    public void setDimensionality(String dimensionality){
        this.dimensionality = dimensionality;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getSubordinate(){
        return subordinate;
    }

    public void setSubordinate(String subordinate){
        this.subordinate = subordinate;
    }
}
