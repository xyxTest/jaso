package com.yaj.jaso.business.menu.entity.vo;

import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.global.PageVo;

public class MenuVo extends MenuPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
