package com.boot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.sql.Timestamp;



import org.apache.commons.lang3.time.DateFormatUtils;

import ch.qos.logback.core.boolex.Matcher;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
		/*
		 * System.out.println(checkFormat("2015-01-01", "yyyyMMdd"));
		 * System.out.println(checkFormat("2015-01-01", "yyyy-MM-dd"));
		 */
        System.out.println(getDateTime());
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static boolean checkFormat(String str, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = (Date) formatter.parse(str);
            System.out.println(str);
            System.out.println(formatter.format(date));
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }
    


	/**

	 * String(yyyy-MM-dd HH:mm:ss) 转 Date

	 * 

	 * @param time

	 * @return

	 * @throws ParseException

	 */

	// String date = "2010/05/04 12:34:23";

	public static Date StringToDate(String time) throws ParseException {

		

		Date date = new Date();

		// 注意format的格式要与日期String的格式相匹配

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			date = dateFormat.parse(time);

			System.out.println(date.toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

 

		return date;

	}

 

	/**

	 * Date转为String(yyyy-MM-dd HH:mm:ss)

	 * 

	 * @param time

	 * @return

	 */

	public static String DateToString(Date time) {

		String dateStr = "";

		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");

		try {

			dateStr = dateFormat.format(time);

			System.out.println(dateStr);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return dateStr;

	}

	/**

	 * String(yyyy-MM-dd HH:mm:ss)转10位时间戳

	 * @param time

	 * @return

	 */

	public static Integer StringToTimestamp(String time){

   

        int times = 0;

        try {  

            times = (int) ((Timestamp.valueOf(time).getTime())/1000);  

        } catch (Exception e) {  

            e.printStackTrace();  

        }

        if(times==0){

        	System.out.println("String转10位时间戳失败");

        }

		return times; 

		

	}

	/**

	 * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)

	 * @param time

	 * @return

	 */

	public static String timestampToString(Integer time){

		//int转long时，先进行转型再进行计算，否则会是计算结束后在转型

		long temp = (long)time*1000;

		Timestamp ts = new Timestamp(temp);  

        String tsStr = "";  

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

        try {  

            //方法一  

            tsStr = dateFormat.format(ts);  

            System.out.println(tsStr);  

        } catch (Exception e) {  

            e.printStackTrace();  

        }

		return tsStr;  

	}

	/**

	 * 10位时间戳转Date

	 * @param time

	 * @return

	 */

	public static Date TimestampToDate(Integer time){

		long temp = (long)time*1000;

		Timestamp ts = new Timestamp(temp);  

        Date date = new Date();  

        try {  

            date = ts;  

            //System.out.println(date);  

        } catch (Exception e) {  

            e.printStackTrace();  

        }  

        return date;

	}

	/**

	 * Date类型转换为10位时间戳

	 * @param time

	 * @return

	 */

	public static Integer DateToTimestamp(Date time){

		Timestamp ts = new Timestamp(time.getTime());

		

		return (int) ((ts.getTime())/1000);

	}    
    
	/**

	 * 得到8位的当前日期串20190903

	 * @param time

	 * @return

	 */

	public static String DateToStr8(Date time){
		
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		return f.format(time).toString();

	}    
	/**
     * @Author：
     * @Description：更加输入日期，获取输入日期的前一天
     * @Date：
     * @strData：参数格式：yyyy-MM-dd
     * @return：返回格式：yyyy-MM-dd
     */
    public static String getNextDateByDate8(String strData) {
        String preDate = "";
         Calendar c = Calendar.getInstance();
         SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
         Date date = null;
         try {
             date = sdf.parse(strData);
         } catch (java.text.ParseException e) {
             e.printStackTrace();
         }
         
         c.setTime(date);
         int day1 = c.get(Calendar.DATE);
         c.set(Calendar.DATE, day1 +1);
         preDate = sdf.format(c.getTime());
        return preDate;
    }
    
    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        java.util.regex.Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }   
    /**
     * @Author：
     * @Description：更加输入日期，获取输入日期的前一天
     * @Date：
     * @strData：参数格式：yyyy-MM-dd
     * @return：返回格式：yyyy-MM-dd
     */
    public static String getPreDateByDate8(String strData) {
        String preDate = "";
         Calendar c = Calendar.getInstance();
         SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
         Date date = null;
         try {
             date = sdf.parse(strData);
         } catch (java.text.ParseException e) {
             e.printStackTrace();
         }
         
         c.setTime(date);
         int day1 = c.get(Calendar.DATE);
         c.set(Calendar.DATE, day1 -1);
         preDate = sdf.format(c.getTime());
        return preDate;
    }
    public static int daysBetween(Date smdate,Date bdate) throws ParseException

    {

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    smdate=sdf.parse(sdf.format(smdate));

    bdate=sdf.parse(sdf.format(bdate));

    Calendar cal = Calendar.getInstance();

    cal.setTime(smdate);

    long time1 = cal.getTimeInMillis();

    cal.setTime(bdate);

    long time2 = cal.getTimeInMillis();

    long between_days=(time2-time1)/(1000*3600*24);

    return Integer.parseInt(String.valueOf(between_days));

    }

    /**

    *字符串的日期格式的计算

    */

    public static int daysBetween(String smdate,String bdate) throws ParseException{

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    Calendar cal = Calendar.getInstance();

    cal.setTime(sdf.parse(smdate));

    long time1 = cal.getTimeInMillis();

    cal.setTime(sdf.parse(bdate));

    long time2 = cal.getTimeInMillis();

    long between_days=(time2-time1)/(1000*3600*24);

    return Integer.parseInt(String.valueOf(between_days));

    }
    
    
}
