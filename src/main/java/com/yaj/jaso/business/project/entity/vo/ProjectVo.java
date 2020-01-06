package com.yaj.jaso.business.project.entity.vo;

import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.global.PageVo;

public class ProjectVo extends ProjectPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
