package com.yaj.jaso.business.measureproblemuser.entity.vo;

import java.util.List;

import com.yaj.jaso.business.measureproblemuser.entity.po.MeasureProblemUserPO;

public class MeasureProblemUserVo extends MeasureProblemUserPO{
	private List<Long> jasoUserIds;

	public List<Long> getJasoUserIds() {
		return jasoUserIds;
	}

	public void setJasoUserIds(List<Long> jasoUserIds) {
		this.jasoUserIds = jasoUserIds;
	}
	
}
