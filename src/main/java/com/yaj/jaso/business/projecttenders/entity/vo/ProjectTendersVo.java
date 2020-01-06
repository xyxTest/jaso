package com.yaj.jaso.business.projecttenders.entity.vo;

import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;
import com.yaj.jaso.global.PageVo;

public class ProjectTendersVo extends ProjectTendersPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
