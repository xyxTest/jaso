package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.List;

import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;

public class MeasureRoomList {
	private Long measureSiteId;//房间id
	private Long measurePaperIds;
	private String measureSiteName;//房间名称
	private Long apartmentId;
	private List<MeasurePointPO> measureRoomPointInfo;//每个房间的的所有测点信息
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
	public List<MeasurePointPO> getMeasureRoomPointInfo() {
		return measureRoomPointInfo;
	}
	public void setMeasureRoomPointInfo(List<MeasurePointPO> measureRoomPointInfo) {
		this.measureRoomPointInfo = measureRoomPointInfo;
	}
	public Long getMeasurePaperIds() {
		return measurePaperIds;
	}
	public void setMeasurePaperIds(Long measurePaperIds) {
		this.measurePaperIds = measurePaperIds;
	}
	public Long getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}
	
}
