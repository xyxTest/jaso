package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.Date;

public class MeasureSiteWebResult {
	private Long measureSiteId;
	private Long measurePaperId;
	private Integer majorType;
	private Long jasoUserId;
	private String userRealName;
	private String measureSiteName;
	private String measureNum;
	private Integer doneNum;
	private Integer allNum;
	private Date createTime;
	public Long getMeasureSiteId() {
		return measureSiteId;
	}
	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}
	public Long getMeasurePaperId() {
		return measurePaperId;
	}
	public void setMeasurePaperId(Long measurePaperId) {
		this.measurePaperId = measurePaperId;
	}
	public Long getJasoUserId() {
		return jasoUserId;
	}
	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getMeasureSiteName() {
		return measureSiteName;
	}
	public void setMeasureSiteName(String measureSiteName) {
		this.measureSiteName = measureSiteName;
	}
	public Integer getDoneNum() {
		return doneNum;
	}
	public void setDoneNum(Integer doneNum) {
		this.doneNum = doneNum;
	}
	public Integer getAllNum() {
		return allNum;
	}
	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getMajorType() {
		return majorType;
	}
	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}
	public String getMeasureNum() {
		return measureNum;
	}
	public void setMeasureNum(String measureNum) {
		this.measureNum = measureNum;
	}
	
}
