package com.yaj.jaso.business.machinery.entity.vo;

import com.yaj.jaso.business.machinery.entity.po.MachineryPO;
import com.yaj.jaso.global.PageVo;

public class MachineryVo extends MachineryPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
