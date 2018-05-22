package com.solr.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy. 2018/5/22  10:39
 */
public class Test {
    public static void main(String[] args) {

        Boolean flag = true;
        List<String> list = new ArrayList<>();
        while(flag){
            list.add("11");
            System.out.println(list.size()+"mm");
            if(list.size()==5){
                break;
            }
            System.out.println(list.size());
            if(list.size()==10){
                flag = false;
            }
        }
        System.out.println("-------------------");
    }
}
