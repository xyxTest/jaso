package com.yaj.jaso.business.measurepaper.entity.vo;

import java.util.List;

import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;

public class MeasurePointInfo extends MeasurePointPO{
	private List<PointCheckResult> pointCheckList;

	public List<PointCheckResult> getPointCheckList() {
		return pointCheckList;
	}

	public void setPointCheckList(List<PointCheckResult> pointCheckList) {
		this.pointCheckList = pointCheckList;
	}

}
