package com.yaj.jaso.business.attencelog.entity.vo;

import com.yaj.jaso.business.attencelog.entity.po.AttenceLogPO;
import com.yaj.jaso.global.PageVo;

public class AttenceLogVo extends AttenceLogPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
