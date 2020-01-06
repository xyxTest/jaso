package com.yaj.jaso.business.nature.entity.vo;

import com.yaj.jaso.business.nature.entity.po.NaturePO;
import com.yaj.jaso.global.PageVo;

public class NatureVo extends NaturePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
