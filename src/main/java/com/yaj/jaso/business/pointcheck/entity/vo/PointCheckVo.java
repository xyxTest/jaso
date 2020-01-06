package com.yaj.jaso.business.pointcheck.entity.vo;

import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.global.PageVo;

public class PointCheckVo extends PointCheckPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
