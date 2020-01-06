package com.yaj.jaso.business.measuresite.entity.vo;

import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;
import com.yaj.jaso.global.PageVo;

public class MeasureSiteVo extends MeasureSitePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
