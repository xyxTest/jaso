package com.yaj.jaso.business.rolemenu.entity.vo;

import com.yaj.jaso.business.rolemenu.entity.po.RoleMenuPO;
import com.yaj.jaso.global.PageVo;

public class RoleMenuVo extends RoleMenuPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
