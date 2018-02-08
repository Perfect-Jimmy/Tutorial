package com.tutorial.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Jimmy. 2018/2/8  21:59
 */
public class DateUtil {

    /**
     * Date转换为字符串
     * @return
     */
    public static String dateToString(Date date){
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return null;
    }

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toLocalDate().toString());
    }
}
