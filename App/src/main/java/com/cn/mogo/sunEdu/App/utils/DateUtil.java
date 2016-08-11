package com.cn.mogo.sunEdu.App.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by FE on 2016/6/28.
 */
public class DateUtil {

    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YYMMDD = "yyyy-MM-dd";

    public static Date addDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);//让日期加1
        calendar.get(Calendar.DATE);//加1之后的日期Top
        return calendar.getTime();
    }

    /**
     * 返回两个日期相差的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daySub(Date fDate, Date oDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long to = fDate.getTime();
        long from = oDate.getTime();
        return (int)(to - from) / (1000 * 60 * 60 * 24);
    }

    /**
     * @param _date
     * @return
     */
    public static String formatDate(Date _date) {
        if( _date == null ){
            return "1970-01-01";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_date);
    }

}
