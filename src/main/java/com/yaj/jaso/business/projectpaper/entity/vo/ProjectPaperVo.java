package com.yaj.jaso.business.projectpaper.entity.vo;

import com.yaj.jaso.business.projectpaper.entity.po.ProjectPaperPO;
import com.yaj.jaso.global.PageVo;

public class ProjectPaperVo extends ProjectPaperPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
