package com.example.algorithm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:18
 */
public class TimeUtil {
    public static String getDate(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String time = ft.format(date);
        return time;
    }
}
