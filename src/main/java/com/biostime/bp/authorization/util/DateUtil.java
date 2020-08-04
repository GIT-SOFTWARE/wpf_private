package com.biostime.bp.authorization.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    
    public static final String YYYY_MM="yyyy-MM";
    public static final String YYYY_MM_DD="yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM="yyyy_MM_dd_HH_mm";
    public static final String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    public static Date getMonthFirstDay(Date date)
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        
        // 获取某月最小天数
        int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        c.set(Calendar.DAY_OF_MONTH, firstDay);
        
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }
    
    public static Date getMonthLastDay(Date date)
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        // 获取某月最大天数
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        c.set(Calendar.DAY_OF_MONTH, lastDay); 
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
    
    
    
    public static Date setDate(Date date,int calendarType,int calendarValue)
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        c.set(calendarType, calendarValue); 
        return c.getTime();
    }
    
    public static Date addDate(Date date,int calendarType,int calendarValue)
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        c.add(calendarType, calendarValue); 
        return c.getTime();
    }
    
    public static int getDateByCalendar(Date date,int calendarType)
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        return c.get(calendarType);
    }
    
    public static String getDateForStr(Date date,String format)
    {
        SimpleDateFormat sf=new SimpleDateFormat(format);
        return sf.format(date);
    }
    
    
    public static Date toDateByStr(String date,String format)
    {
        SimpleDateFormat sf=new SimpleDateFormat(format);
        Date returnDate=null;
        try
        {
            returnDate= sf.parse(date);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return returnDate;
    }
    
    public static Date toDateByStrNAndLenient(String date,String format,boolean lenient)
    {
        SimpleDateFormat sf=new SimpleDateFormat(format);
        Date returnDate=null;
        try
        {
            sf.setLenient(lenient);  
            returnDate= sf.parse(date);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return returnDate;
    }
    
    
}
