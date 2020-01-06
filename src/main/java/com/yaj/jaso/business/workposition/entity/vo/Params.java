package com.yaj.jaso.business.workposition.entity.vo;

import java.math.BigDecimal;

public class Params {
	/*
    *经度
    */
    private BigDecimal positionLongitude;
    /*
    *纬度
    */
    private BigDecimal positionLatitude;
    private Integer workRecordType;
    
	public BigDecimal getPositionLongitude() {
		return positionLongitude;
	}
	public void setPositionLongitude(BigDecimal positionLongitude) {
		this.positionLongitude = positionLongitude;
	}
	public BigDecimal getPositionLatitude() {
		return positionLatitude;
	}
	public void setPositionLatitude(BigDecimal positionLatitude) {
		this.positionLatitude = positionLatitude;
	}
	public Integer getWorkRecordType() {
		return workRecordType;
	}
	public void setWorkRecordType(Integer workRecordType) {
		this.workRecordType = workRecordType;
	}
}
