package com.boot.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentWeek {
    private   static Date getFirstWeek(){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayweek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayweek==1) {
            calendar.add(Calendar.DAY_OF_MONTH,-1);
        }
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE,calendar.getFirstDayOfWeek()-i);
        return calendar.getTime();
    }
    public static  Date getCurrenproDate(){
        return getFirstWeek();
    }
    public static  String getCurrenproDay(String format){
        String  result="";
        if (StringUtils.isEmpty(format)) {
            result=   new SimpleDateFormat("yyyy.MM.dd").format(getFirstWeek().getTime());
        }else {
            try {
                result=   new SimpleDateFormat(format).format(getFirstWeek().getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public  static Date getCurrenaftDate(){
        Date firstWeek = getFirstWeek();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstWeek);
        calendar.add(Calendar.DAY_OF_MONTH,4);

        return calendar.getTime();
    }
    public  static String getCurrenaftDay(String format){
        String  result="";
        Date firstWeek = getFirstWeek();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstWeek);
        calendar.add(Calendar.DAY_OF_MONTH,4);
        if (StringUtils.isEmpty(format)) {
            result=   new SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime());
        }else {
            try {
                result=   new SimpleDateFormat(format).format(calendar.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public  static  String getWeek(){
        Date firstWeek = getFirstWeek();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstWeek);
        return String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH));
    }
    public  static  String getMonth(){
        Date firstWeek = getFirstWeek();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstWeek);
        return String.valueOf(calendar.get(Calendar.MONDAY)+1);
    }
    public  static  String getYear(){
        Date firstWeek = getFirstWeek();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstWeek);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(CurrentWeek.getYear());
    }
}
