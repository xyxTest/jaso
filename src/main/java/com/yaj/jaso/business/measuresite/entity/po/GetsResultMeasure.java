package com.yaj.jaso.business.measuresite.entity.po;

public class GetsResultMeasure extends MeasureSitePO {
	private String apartmentName;
	private String userRealName;
	private Integer doneNum=0;
	private Integer allNum=0;
	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getDoneNum() {
		return doneNum;
	}

	public void setDoneNum(Integer doneNum) {
		this.doneNum = doneNum;
	}
}
