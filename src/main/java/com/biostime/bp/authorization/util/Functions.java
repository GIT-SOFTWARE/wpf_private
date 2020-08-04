package com.biostime.bp.authorization.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {
    private static Logger log = LoggerFactory.getLogger(Functions.class);
    private static SimpleDateFormat FORMAT = new SimpleDateFormat();
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YMDHM_CHINESE = "yyyy年MM月dd日 HH时mm分";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YM = "yyyy-MM";

    /**
     * String convert to Integer
     *
     * @param str
     * @param def
     * @return
     */
    public static Integer str2Integer(String str, Integer def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    public static Integer obj2Integer(Object obj, Integer def) {
        if (obj == null) {
            return def;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Float
     *
     * @param str
     * @param def
     * @return
     */
    public static Float str2Float(String str, Float def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Double
     *
     * @param str
     * @param def
     * @return
     */
    public static Double str2Double(String str, Double def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Long
     *
     * @param str
     * @param def
     * @return
     */
    public static Long str2Long(String str, Long def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    public static Long obj2Long(Object obj, Long def) {
        if (obj == null) {
            return def;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Date
     *
     * @param str
     * @param pattern
     * @param def
     * @return
     */
    public static Date str2Date(String str, String pattern, Date def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            FORMAT.applyPattern(pattern);
            return FORMAT.parse(str);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Date 字符串转换成日期,格式 yyyy-MM-dd
     *
     * @param str
     * @param def
     * @return
     */
    public static Date str2DateYMD(String str, Date def) {
        return str2Date(str, YMD, def);
    }

    /**
     * String convert to Date 字符串转换成日期,格式 yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @param def
     * @return
     */
    public static Date str2DateYMDHMS(String str, Date def) {
        return str2Date(str, YMDHMS, def);
    }

    /**
     * Date convert to String
     *
     * @param date
     * @param pattern
     * @param def
     * @return
     */
    public static String date2String(Date date, String pattern, String def) {
        if (date == null) {
            return def;
        }
        try {
            FORMAT.applyPattern(pattern);
            return FORMAT.format(date);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * String convert to Date and add day
     *
     * @param str
     * @param pattern
     * @param def
     * @return
     */
    public static Date str2DateAndAddDay(String str, String pattern, int day_of_month, Date def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        try {
            FORMAT.applyPattern(pattern);
            Calendar cal = Calendar.getInstance();
            cal.setTime(FORMAT.parse(str));
            cal.add(Calendar.DAY_OF_MONTH, day_of_month);
            return cal.getTime();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return def;
        }
    }

    /**
     * 方法说明：操作日期 加一天 开发： chenbai 创建时间： Mar 25, 2014
     *
     * @param date
     * @param pattern
     * @param day_of_month
     * @return
     */
    public static Date addDay(Date date, String pattern, int day_of_month) {
        try {
            FORMAT.applyPattern(pattern);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, day_of_month);
            return cal.getTime();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return date;
        }
    }

    /**
     * 方法描述：操作现在的时间
     *
     * @param:
     * @return:
     * @version: 1.0
     * @author: tkylin@vip.qq.com
     * @version: 2012-10-17 下午4:51:07
     */
    public static Date addByNow(int feild, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(feild, amount);
        return cal.getTime();
    }

    /**
     * 冒泡排序
     *
     * @param ints
     * @return
     */
    public static int[] sort(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[i] > ints[j]) {
                    ints[i] = ints[i] ^ ints[j];
                    ints[j] = ints[i] ^ ints[j];
                    ints[i] = ints[i] ^ ints[j];
                }
            }
        }
        return ints;
    }


    
    /**
     * 方法描述：获取URL固定参数值
     *
     * @param:
     * @return:
     * @version: 1.0
     * @author: tkylin@vip.qq.com
     * @version: 2013-7-12 下午5:41:13
     */
    public static String getUrlParamsValue(String url, String paramName) {
        Pattern pattern = Pattern.compile("(^|\\?|&)" + paramName + "=([^&]*)(&|$)");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group(2);
        }
        return "";
    }

    public static Date getDateFromLongTimes(String str, Date def) {
        Date result = def;
        long tims = 0L;
        if (StringUtils.isBlank(str)) {
            return result;
        }
        try {
            tims = Long.parseLong(str);
            result = new Date();
            result.setTime(tims);
            // FORMAT.parse(date.)
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return result;
    }

    public static String formatString(String str) {
        str = str.replace(" ", "&nbsp;");
        str = str.replace("<", "&lt;");
        str = str.replace(">", "&gt;");
        str = str.replace("\n", "<br>");
        return str;
    }

    /**
     * 方法说明：date to String for fomat
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateFormat(Date date, String format) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return null;
    }

   

    public static String strSubByLength(String str, int len) {
        if ("".equals(str)) {
            return "";
        }
        if (str.length() > len) {
            str = str.substring(0, len) + "... ...";
        }
        return str;
    }

    /**
     * 计算两个日期的相差天数  
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
    }
    
    /**
     * 将日期转换为 YMD格式
     * @param date
     * @return
     */
    public static Date dateToYMD(Date date) {
         return str2DateYMD(dateFormat(date,Functions.YMD), null);
    }
    
    
    /**
     * 将日期转换为 YMD格式
     * @param date
     * @return
     */
    public static String date2StringYM(Date date) {
        return dateFormat(date,Functions.YM);
    }

    /** 
     *  
     * 获取上个月的最后一天.  xxxx-xx-xx 23:59:59
     *  
     * @return 
     */  
    public static Date getCurrentMonthEndDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();  
    } 
    
    /** 
     *  
     * 获取上个月的第一天.  xxxx-xx-xx 00:00:00
     *  
     * @return 
     */  
    public static Date getCurrentMonthStartDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();  
    } 
    
    /** 
     *  
     * 根据日期获取当前日期 当月的最后一天.  xxxx-xx-xx 23:59:59
     *  
     * @return 
     */  
    public static Date getCurrentMonthEndDate(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();  
    } 
    
    /** 
     *  
     * 根据日期获取当前日期 当月的第一天.  xxxx-xx-xx 00:00:00
     *  
     * @return 
     */  
    public static Date getCurrentMonthStartDate(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();  
    } 
    
    
    
    /**
     * 获取昨天开始时间
     * @return
     */
    public static Date getYesterdayStartDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();  
    }  
    
    /**
     * 获取昨天结束时间
     * @return
     */
    public static Date getYesterdayEndDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();  
    }  
    
    
    /**
     * 获取今天的的结束时间
     */
    public static Date getNowEndDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();  
    }
    
    /**
     * 获取今天的开始时间
     * @return
     */
    public static Date getNowStartDate(){
   	 Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();
   }
    
    /**
     * 获取今天上周的时间  包含今天
     * @return
     */
    public static  Date getNowLastWeekDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.DAY_OF_MONTH, -6);  
        return calendar.getTime();  
    }  
    
    
   /**
    * 获取今天上个月的时间  30 天  包含今天
    */
    public static  Date getNowLastMonthDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.DAY_OF_MONTH, -29);  
        return calendar.getTime();  
    }  
    
 
    
    
    /**
     * 获取日期当天的开始时间
     * @return
     */
    public static Date getStartTimeByDate(Date date){
    	 Calendar calendar = Calendar.getInstance();  
         calendar.setTime(date);  
         calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
         calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
         calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
         return calendar.getTime();
    }
    
    
    /**
     * 获取日期当天的结束时间
     * @return
     */
    public static Date getEndTimeByDate(Date date){
    	 Calendar calendar = Calendar.getInstance();  
         calendar.setTime(date);  
         calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
         calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
         calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
         return calendar.getTime();
    }
    
    public static Date addMonthsByDate(Date date,int value ){
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.MONTH, value);
        return  calendar.getTime();
    }
    
 
    /**
             *当前时间是否介于 两个时间范围内
     * @param startTime
     * @param endTime
     * @return
     */
	public static boolean currentDateIsWithinADateRange(Date startTime, Date endTime) {
		Date now = new Date();
		return now.after(startTime) && now.before(endTime);
	}
    
    
    
    public static void main(String[] args) {
    	Date startDate = str2DateYMDHMS("2018-11-14 10:00:00", null);
		Date endDate = str2DateYMDHMS("2018-11-14 13:00:00", null);
		System.out.println(getStartTimeByDate(startDate).toLocaleString());
	/*	Date startDate = str2DateYMD("2017-05-01 23:00:00", null);
		Date endDate = str2DateYMD("2017-07-01 16:00:00", null);
		
		test(getCurrentMonthStartDate(startDate));
		test(getCurrentMonthEndDate(startDate));
		test(getCurrentMonthStartDate());
		test(getCurrentMonthEndDate());*/
	}
    
  /*  public  static void test(Date date){
    	System.out.println(date2String(date, YMDHMS, null));
    }*/
    


}
