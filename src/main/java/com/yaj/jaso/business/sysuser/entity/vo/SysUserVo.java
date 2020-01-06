package com.yaj.jaso.business.sysuser.entity.vo;

import com.yaj.jaso.business.sysuser.entity.po.SysUserPO;
import com.yaj.jaso.global.PageVo;

public class SysUserVo extends SysUserPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
