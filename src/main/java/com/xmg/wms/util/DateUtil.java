package com.xmg.wms.util;

import java.util.Calendar;
import java.util.Date;

//处理日期的工具类
public class DateUtil {
    //工具类结扎...
    private DateUtil(){}
    public static Date getBeginDate(Date currentDate){
        //开始时间 0时0分0秒
        if(currentDate==null){
            return null;
        }
        //获取Calendar对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    public static Date getEndDate(Date currentDate){
        //开始时间 23时59分59秒
        if(currentDate==null){
            return null;
        }
        //获取Calendar对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }
}
