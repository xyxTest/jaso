package com.yaj.jaso.business.measurepoint.entity.vo;

import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.global.PageVo;

public class MeasurePointVo extends MeasurePointPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
