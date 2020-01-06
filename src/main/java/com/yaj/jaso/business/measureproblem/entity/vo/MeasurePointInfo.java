package com.yaj.jaso.business.measureproblem.entity.vo;

public class MeasurePointInfo {
	private MeasureProblemResult measureProblem;
	private Object userInfo;
	private Integer x;
	private Integer y;
	private String paperUrl;
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getPaperUrl() {
		return paperUrl;
	}
	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}
	public Object getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
	public MeasureProblemResult getMeasureProblem() {
		return measureProblem;
	}
	public void setMeasureProblem(MeasureProblemResult measureProblem) {
		this.measureProblem = measureProblem;
	}
	
}
