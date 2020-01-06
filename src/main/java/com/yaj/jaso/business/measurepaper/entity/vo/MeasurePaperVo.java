package com.yaj.jaso.business.measurepaper.entity.vo;

import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.global.PageVo;

public class MeasurePaperVo extends MeasurePaperPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
