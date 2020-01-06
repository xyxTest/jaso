package com.yaj.jaso.business.pointcheck.entity.vo;

import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;

public class PointChecks extends PointCheckPO {
	private Long measureSiteId;

	public Long getMeasureSiteId() {
		return measureSiteId;
	}

	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}
	
}
