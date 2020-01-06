package com.yaj.jaso.business.materialtype.entity.vo;

import com.yaj.jaso.business.materialtype.entity.MaterialTypePO;
import com.yaj.jaso.global.PageVo;

public class MaterialTypeVo extends MaterialTypePO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
