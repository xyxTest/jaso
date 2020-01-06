package com.yaj.jaso.business.userrole.entity.vo;

import com.yaj.jaso.business.userrole.entity.po.UserRolePO;
import com.yaj.jaso.global.PageVo;

public class UserRoleVo extends UserRolePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
