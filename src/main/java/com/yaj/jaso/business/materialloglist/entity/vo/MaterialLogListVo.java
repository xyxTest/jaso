package com.yaj.jaso.business.materialloglist.entity.vo;

import com.yaj.jaso.business.materialloglist.entity.po.MaterialLogListPO;
import com.yaj.jaso.global.PageVo;

public class MaterialLogListVo extends MaterialLogListPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
