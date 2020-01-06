package com.yaj.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecenttimeUtil {
	  private static  SimpleDateFormat longHourSdf  = new SimpleDateFormat("yyyy-MM-dd HH");
	  private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); ;
	  private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
	    
	    /**
	     * 获得本周的第一天，周一
	     * 
	     * @return
	     */
	    public static  Date getCurrentWeekDayStartTime() {
	        Calendar c = Calendar.getInstance();
	        try {
	            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
	            c.add(Calendar.DATE, -weekday);
	            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return c.getTime();
	    }

	    /**
	     * 获得本周的最后一天，周日
	     * 
	     * @return
	     */
	    public static  Date getCurrentWeekDayEndTime() {
	        Calendar c = Calendar.getInstance();
	        try {
	            int weekday = c.get(Calendar.DAY_OF_WEEK);
	            c.add(Calendar.DATE, 8 - weekday);
	            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return c.getTime();
	    }

	    /**
	     * 获得本天的开始时间，即2012-01-01 00:00:00
	     * 
	     * @return
	     */
	    public static  Date getCurrentDayStartTime() {
	        Date now = new Date();
	        try {
	            now = shortSdf.parse(shortSdf.format(now));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 获得本天的结束时间，即2012-01-01 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentDayEndTime() {
	        Date now = new Date();
	        try {
	            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 获得本小时的开始时间，即2012-01-01 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentHourStartTime() {
	        Date now = new Date();
	        try {
	            now = longHourSdf.parse(longHourSdf.format(now));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 获得本小时的结束时间，即2012-01-01 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentHourEndTime() {
	        Date now = new Date();
	        try {
	            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 获得本月的开始时间，即2012-01-01 00:00:00
	     * 
	     * @return
	     */
	    public static  Date getCurrentMonthStartTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.DATE, 1);
	            now = shortSdf.parse(shortSdf.format(c.getTime()));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前月的结束时间，即2012-01-31 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentMonthEndTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.DATE, 1);
	            c.add(Calendar.MONTH, 1);
	            c.add(Calendar.DATE, -1);
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前年的开始时间，即2012-01-01 00:00:00
	     * 
	     * @return
	     */
	    public static  Date getCurrentYearStartTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.MONTH, 0);
	            c.set(Calendar.DATE, 1);
	            now = shortSdf.parse(shortSdf.format(c.getTime()));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前年的结束时间，即2012-12-31 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentYearEndTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.MONTH, 11);
	            c.set(Calendar.DATE, 31);
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前季度的开始时间，即2012-01-1 00:00:00
	     * 
	     * @return
	     */
	    public static  Date getCurrentQuarterStartTime() {
	        Calendar c = Calendar.getInstance();
	        int currentMonth = c.get(Calendar.MONTH) + 1;
	        Date now = null;
	        try {
	            if (currentMonth >= 1 && currentMonth <= 3)
	                c.set(Calendar.MONTH, 0);
	            else if (currentMonth >= 4 && currentMonth <= 6)
	                c.set(Calendar.MONTH, 3);
	            else if (currentMonth >= 7 && currentMonth <= 9)
	                c.set(Calendar.MONTH, 4);
	            else if (currentMonth >= 10 && currentMonth <= 12)
	                c.set(Calendar.MONTH, 9);
	            c.set(Calendar.DATE, 1);
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前季度的结束时间，即2012-03-31 23:59:59
	     * 
	     * @return
	     */
	    public static  Date getCurrentQuarterEndTime() {
	        Calendar c = Calendar.getInstance();
	        int currentMonth = c.get(Calendar.MONTH) + 1;
	        Date now = null;
	        try {
	            if (currentMonth >= 1 && currentMonth <= 3) {
	                c.set(Calendar.MONTH, 2);
	                c.set(Calendar.DATE, 31);
	            } else if (currentMonth >= 4 && currentMonth <= 6) {
	                c.set(Calendar.MONTH, 5);
	                c.set(Calendar.DATE, 30);
	            } else if (currentMonth >= 7 && currentMonth <= 9) {
	                c.set(Calendar.MONTH, 8);
	                c.set(Calendar.DATE, 30);
	            } else if (currentMonth >= 10 && currentMonth <= 12) {
	                c.set(Calendar.MONTH, 11);
	                c.set(Calendar.DATE, 31);
	            }
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }
	    /**
	     * 获取前/后半年的开始时间
	     * @return
	     */
	    public static  Date getHalfYearStartTime(){
	        Calendar c = Calendar.getInstance();
	        int currentMonth = c.get(Calendar.MONTH) + 1;
	        Date now = null;
	        try {
	            if (currentMonth >= 1 && currentMonth <= 6){
	                c.set(Calendar.MONTH, 0);
	            }else if (currentMonth >= 7 && currentMonth <= 12){
	                c.set(Calendar.MONTH, 6);
	            }
	            c.set(Calendar.DATE, 1);
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	        
	    }
	    /**
	     * 获取前/后半年的结束时间
	     * @return
	     */
	    public static  Date getHalfYearEndTime(){
	        Calendar c = Calendar.getInstance();
	        int currentMonth = c.get(Calendar.MONTH) + 1;
	        Date now = null;
	        try {
	            if (currentMonth >= 1 && currentMonth <= 6){
	                c.set(Calendar.MONTH, 5);
	                c.set(Calendar.DATE, 30);
	            }else if (currentMonth >= 7 && currentMonth <= 12){
	                c.set(Calendar.MONTH, 11);
	                c.set(Calendar.DATE, 31);
	            }
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }
	    
	    

	}

//	/**
//	  * 获取前一天日期 及星期
//	  */
//	public class RemindDateUtils {
//	    private void initDate(){
//	        String[] weekDays = {"周日","周一","周二","周三","周四","周五","周六"};
//	         Calendar cal = Calendar.getInstance();
//	         cal.add(Calendar.Date,-1);
//	         int i = cal.get(Calendar.DAY_OF_WEEK)-1;
//	         if(i<0){
//	            i=0;
//	        }
//	        String yesterday = new SimpleDateFormate("yyyy年MM月dd日").format(cal.getTime());
//	　　　　 String w = weekDays[i]; 
//	    }
//	      
//	}   
//}
