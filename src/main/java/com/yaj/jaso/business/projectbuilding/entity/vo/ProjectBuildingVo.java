package com.yaj.jaso.business.projectbuilding.entity.vo;

import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;
import com.yaj.jaso.global.PageVo;

public class ProjectBuildingVo extends ProjectBuildingPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
