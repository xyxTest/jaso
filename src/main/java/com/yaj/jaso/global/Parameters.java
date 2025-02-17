package com.yaj.jaso.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

public class Parameters {
	public void getUserId(){
		CurrentUserInfo currentUserInfo = new CurrentUserInfo();
		currentUserInfo.setUserType(0);
		JasoUserPO user =(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		currentUserInfo.setUserId(user.getJasoUserId());
		currentUserInfo.setCompanyId(user.getCompanyId());
	}
	private static final String pan = "D";
	private static final String baseFilePath = pan + ":/jasobim/tomcat_8080/webapps/own/";
	public static final String userLogFilePath = baseFilePath  + "userLog/";
	public static final String ductfilePath = baseFilePath + "duct/";
	public static final String quantityPath = baseFilePath + "quantity/";
	public static final String appKey = "1674ebe520e502d71e4de511";
	public static final String masterSecret = "f6b600502ebe3aca7bc48507";
	public static final String[] checkContent={"户内强电箱","户内弱电箱","盒子墙面的垂直度","开关","开关","厨房插座","厨房插座","客厅或卧室插座","阳台插座","闭路电视","红外幕帘","网络电话","手动报警按钮","手动报警按钮","客厅空调插座","客厅空调插座","插座","插座","坐标","坐标"};
	public static final String[] checkTemplate={"[-5,5]mm","[-1,1]mm"};
	public static final String[] designLevel={"1800","500","","1300","150~200","1300","","850","1300","850","2400","750","750","500","2500","300","300",""};
	public static final String[] locationSize={"1600","300","","标高","门边","1300","","850","1300","850","2400","750","750","500","2500","301","300",""};
	public static final String[] checkContentFanglei={"户内强电箱","户内弱电箱","盒子墙面的垂直度","开关","开关","厨房插座","厨房插座","客厅或卧室插座","阳台插座","闭路电视","红外幕帘","网络电话","手动报警按钮","手动报警按钮","客厅空调插座","客厅空调插座","插座","插座","坐标","坐标"};
	public static final String[] checkContentXianhe={};
	public static final String[] projectFilesType={"图纸会审 ","施工组织设计","专项方案","签证资料","人员备案（特殊工种）","花名册","施工日志","图纸深化及翻样","施工资料","竣工验收资料","竣工图","材料复检报告","第三方强制检测","样板实施计划","材料进度计划"};
	public static final String[][] projectDatas={{"",},{}};
	public static final String[] professionList={"电气","给排水","其他","消防","地  库 通 风 安 装 工 程"};
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private static final 
	public static SimpleDateFormat getSdf() {
		return sdf;
	} 
	private static final SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat sdfDay=new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat getSdfs() {
		return sdfs;
	}
	public static SimpleDateFormat getSdfday() {
		return sdfDay;
	} 
	public static int getDaysByYearMonth(int year, int month) {
		  
		         Calendar a = Calendar.getInstance();
		         a.set(Calendar.YEAR, year);
		          a.set(Calendar.MONTH, month - 1);
		         a.set(Calendar.DATE, 1);
		         a.roll(Calendar.DATE, -1);
		         int maxDate = a.get(Calendar.DATE);
		         return maxDate;
	}
	
	public static String getFileName(String name){
		
		String[] str=name.split("\\.");
		String newNames="";
		for(int i=0;i<str.length-1;i++){
			newNames+=str[i];
		}
		return newNames;
		
	}
	public static void main(String[] arg){
		System.out.println(getFileType("asadad.mp3"));
	}
	
	public static String getFileType(String name){
		
		String[] str=name.split("\\.");
		String newNames=str[str.length-1];
		return newNames;
		
	}
	public static String exchangePath(String input){
		String[] re=input.split("\\/");
		String newstr="b";
		for(int i=0;i<re.length;i++){
			if(newstr.equals("b")){
				newstr=re[i];
			}else{
				newstr=newstr+"\\"+re[i];
			}
		}
		return newstr;
	}
	//截取数字  
		public static String getNumbers(String content) {  
		    Pattern pattern = Pattern.compile("\\d+");  
		    Matcher matcher = pattern.matcher(content);  
		    while (matcher.find()) {  
		       return matcher.group(0);  
		    }  
		    return "";  
		}  
		public static boolean HasDigit(String content) {
		    boolean flag = false;
		    Pattern p = Pattern.compile(".*\\d+.*");
		    Matcher m = p.matcher(content);
		    if (m.matches()) {
		        flag = true;
		    }
		    return flag;
		}
		 /**
	     * 日期转星期
	     * 
	     * @param datetime
	     * @return
	     */
	    public static String dateToWeek(String datetime) {
	        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	        String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
	        Calendar cal = Calendar.getInstance(); // 获得一个日历
	        Date datet = null;
            try {
				datet = f.parse(datetime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            cal.setTime(datet);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
	        if (w < 0)
	            w = 0;
	        return weekDays[w];
	    }

		
}
