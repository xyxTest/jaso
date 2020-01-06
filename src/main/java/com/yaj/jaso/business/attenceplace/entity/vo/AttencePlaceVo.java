package com.yaj.jaso.business.attenceplace.entity.vo;

import com.yaj.jaso.business.attenceplace.entity.po.AttencePlacePO;
import com.yaj.jaso.global.PageVo;

public class AttencePlaceVo extends AttencePlacePO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
