package com.yaj.jaso.business.attencemode.entity.vo;

import com.yaj.jaso.business.attencemode.entity.po.AttenceModePO;
import com.yaj.jaso.global.PageVo;

public class AttenceModeVo extends AttenceModePO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
