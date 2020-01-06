package com.yaj.jaso.business.measureproblem.entity.vo;

import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.global.PageVo;

public class MeasureProblemVo extends MeasureProblemPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
