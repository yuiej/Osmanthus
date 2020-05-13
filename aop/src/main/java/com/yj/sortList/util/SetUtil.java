package com.yj.sortList.util;

import java.util.List;

public class SetUtil {

    public static void printArrayList(List<?> list){
        if (list == null && list.size() < 0)
            return;
        System.out.println("*********************");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        System.out.println("*********************");
    }
}
