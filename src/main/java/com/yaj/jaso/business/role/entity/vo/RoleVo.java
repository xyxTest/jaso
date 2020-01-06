package com.yaj.jaso.business.role.entity.vo;

import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.global.PageVo;
public class RoleVo extends RolePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
