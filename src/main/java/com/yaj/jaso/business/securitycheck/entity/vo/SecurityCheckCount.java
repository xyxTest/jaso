package com.yaj.jaso.business.securitycheck.entity.vo;

import java.util.List;

public class SecurityCheckCount {
	private Integer rectifyNum;
	private Integer checkNum;
	private List<Long> rectifyQualityIds;
	private List<Long> checkQualityIds;
	public Integer getRectifyNum() {
		return rectifyNum;
	}
	public void setRectifyNum(Integer rectifyNum) {
		this.rectifyNum = rectifyNum;
	}
	public Integer getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	public List<Long> getRectifyQualityIds() {
		return rectifyQualityIds;
	}
	public void setRectifyQualityIds(List<Long> rectifyQualityIds) {
		this.rectifyQualityIds = rectifyQualityIds;
	}
	public List<Long> getCheckQualityIds() {
		return checkQualityIds;
	}
	public void setCheckQualityIds(List<Long> checkQualityIds) {
		this.checkQualityIds = checkQualityIds;
	}
}
