package com.yaj.jaso.business.measurepoint.entity.vo;

import java.util.List;

import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;

public class MeasurePointAdd extends MeasurePointPO{
	private List<PointCheckPO> pointCheckList;

	public List<PointCheckPO> getPointCheckList() {
		return pointCheckList;
	}

	public void setPointCheckList(List<PointCheckPO> pointCheckList) {
		this.pointCheckList = pointCheckList;
	}
}
