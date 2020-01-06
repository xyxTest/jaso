package com.yaj.jaso.business.measureproblem.entity.vo;

import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;

public class MeasureProblemCheck extends MeasureProblemPO {
	private Integer state;//1、验收通过、2、不通过

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
