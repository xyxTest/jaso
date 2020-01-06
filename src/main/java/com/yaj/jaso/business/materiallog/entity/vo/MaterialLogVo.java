package com.yaj.jaso.business.materiallog.entity.vo;

import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;
import com.yaj.jaso.global.PageVo;

public class MaterialLogVo extends MaterialLogPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

}
