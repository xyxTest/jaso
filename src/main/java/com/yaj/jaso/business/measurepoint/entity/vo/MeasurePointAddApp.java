package com.yaj.jaso.business.measurepoint.entity.vo;

import java.util.List;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.pointcheck.entity.vo.PointCheckInput;

public class MeasurePointAddApp extends MeasurePointPO{
	private List<PointCheckInput> pointCheckList;//每个测点所对应的所有检查项

	public List<PointCheckInput> getPointCheckList() {
		return pointCheckList;
	}

	public void setPointCheckList(List<PointCheckInput> pointCheckList) {
		this.pointCheckList = pointCheckList;
	}

	
}
