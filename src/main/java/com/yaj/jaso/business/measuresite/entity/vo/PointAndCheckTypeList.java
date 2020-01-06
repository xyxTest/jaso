package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.List;

import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.pointcheck.entity.vo.PointCheckInput;

public class PointAndCheckTypeList extends MeasurePointPO{
	private Integer pointStatus;
	private Integer pointType;
	private Long measureSitePointId;
	private List<PointCheckInput> pointCheckList;//每个测点所对应的所有检查项

	public List<PointCheckInput> getPointCheckList() {
		return pointCheckList;
	}

	public void setPointCheckList(List<PointCheckInput> pointCheckList) {
		this.pointCheckList = pointCheckList;
	}



	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

	public Integer getPointStatus() {
		return pointStatus;
	}

	public void setPointStatus(Integer pointStatus) {
		this.pointStatus = pointStatus;
	}

	public Long getMeasureSitePointId() {
		return measureSitePointId;
	}

	public void setMeasureSitePointId(Long measureSitePointId) {
		this.measureSitePointId = measureSitePointId;
	}
}
