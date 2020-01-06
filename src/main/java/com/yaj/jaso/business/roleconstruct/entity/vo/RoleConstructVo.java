package com.yaj.jaso.business.roleconstruct.entity.vo;

import com.yaj.jaso.business.roleconstruct.entity.po.RoleConstructPO;
import com.yaj.jaso.global.PageVo;

public class RoleConstructVo extends RoleConstructPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
