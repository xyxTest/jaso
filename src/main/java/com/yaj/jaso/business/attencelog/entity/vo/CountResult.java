package com.yaj.jaso.business.attencelog.entity.vo;

public class CountResult {
	private Integer workDays;//应到天数
	private Integer lateDays;//迟到天数
	private Integer earlyDays;//早退天数
	private Integer lostDays;//缺卡天数
	private Integer attendanceDays;//出勤天数
	private Integer leaveDays;//旷工天数
	private String workTime;//时间2019-07
	private String userRealName;//姓名
	private String userIcon;//头像
	public Integer getWorkDays() {
		return workDays;
	}
	public void setWorkDays(Integer workDays) {
		this.workDays = workDays;
	}
	public Integer getLateDays() {
		return lateDays;
	}
	public void setLateDays(Integer lateDays) {
		this.lateDays = lateDays;
	}
	public Integer getEarlyDays() {
		return earlyDays;
	}
	public void setEarlyDays(Integer earlyDays) {
		this.earlyDays = earlyDays;
	}
	public Integer getLostDays() {
		return lostDays;
	}
	public void setLostDays(Integer lostDays) {
		this.lostDays = lostDays;
	}
	public Integer getAttendanceDays() {
		return attendanceDays;
	}
	public void setAttendanceDays(Integer attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	public Integer getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(Integer leaveDays) {
		this.leaveDays = leaveDays;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
	
	
}
