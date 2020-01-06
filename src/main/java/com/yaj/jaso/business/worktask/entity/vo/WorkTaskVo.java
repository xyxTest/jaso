package com.yaj.jaso.business.worktask.entity.vo;

import com.yaj.jaso.business.worktask.entity.po.WorkTaskPO;
import com.yaj.jaso.global.PageVo;

public class WorkTaskVo extends WorkTaskPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
