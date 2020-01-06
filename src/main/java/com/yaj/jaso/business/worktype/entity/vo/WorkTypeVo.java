package com.yaj.jaso.business.worktype.entity.vo;

import com.yaj.jaso.business.worktype.entity.po.WorkTypePO;
import com.yaj.jaso.global.PageVo;

public class WorkTypeVo extends WorkTypePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
