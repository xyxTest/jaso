package com.yaj.jaso.business.constructcontent.entity.vo;

import com.yaj.jaso.business.constructcontent.entity.po.ConstructContentPO;
import com.yaj.jaso.global.PageVo;

public class ConstructContentVo extends ConstructContentPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
