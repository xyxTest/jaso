package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.List;

public class MeasureBuildingList {
	private Long measureSiteId;//楼栋id
	private String measureSiteName;//楼栋名称
	private List<MeasureFloorList> measureFloorInfo;//每一栋的所有层信息
	private Integer doneNum;//已测
	private Integer allNum;//总点数
	private Integer problemNum;//爆点
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
	public Integer getProblemNum() {
		return problemNum;
	}
	public void setProblemNum(Integer problemNum) {
		this.problemNum = problemNum;
	}
	public Long getMeasureSiteId() {
		return measureSiteId;
	}
	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}
	public String getMeasureSiteName() {
		return measureSiteName;
	}
	public void setMeasureSiteName(String measureSiteName) {
		this.measureSiteName = measureSiteName;
	}
	public List<MeasureFloorList> getMeasureFloorInfo() {
		return measureFloorInfo;
	}
	public void setMeasureFloorInfo(List<MeasureFloorList> measureFloorInfo) {
		this.measureFloorInfo = measureFloorInfo;
	}
}
