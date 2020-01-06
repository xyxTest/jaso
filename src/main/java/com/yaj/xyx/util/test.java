package com.yaj.xyx.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test {
	public static void main(String[] arg){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());//设置起时间
	    //System.out.println("111111111::::"+cal.getTime());
	    //cal.add(Calendar.YEAR, 1);//增加一年
	    //cal.add(Calendar.DATE, 1);//增加一天  
	    cal.add(Calendar.DATE, -0);//减10天  
	    //cd.add(Calendar.MONTH, 1);//增加一个月   
	    System.out.println("输出::"+cal.getTime());
	}
}
