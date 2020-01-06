package com.yaj.jaso.business.material.entity.vo;

import com.yaj.jaso.business.material.entity.po.MaterialPO;
import com.yaj.jaso.global.PageVo;

public class MaterialVo extends MaterialPO {
	private PageVo pageVo;
	private String sizeOrName;
	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public String getSizeOrName() {
		return sizeOrName;
	}

	public void setSizeOrName(String sizeOrName) {
		this.sizeOrName = sizeOrName;
	}
	
}
