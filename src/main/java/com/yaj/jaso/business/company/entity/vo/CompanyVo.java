package com.yaj.jaso.business.company.entity.vo;

import com.yaj.jaso.business.company.entity.po.CompanyPO;
import com.yaj.jaso.global.PageVo;

public class CompanyVo extends CompanyPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
