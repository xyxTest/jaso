package com.yaj.jaso.business.existingproblem.entity.vo;

import com.yaj.jaso.business.existingproblem.entity.po.ExistingProblemPO;
import com.yaj.jaso.global.PageVo;

public class ExistingProblemVo extends ExistingProblemPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
