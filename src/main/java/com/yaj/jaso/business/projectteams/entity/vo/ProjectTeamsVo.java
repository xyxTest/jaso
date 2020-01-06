package com.yaj.jaso.business.projectteams.entity.vo;

import com.yaj.jaso.business.projectteams.entity.po.ProjectTeamsPO;
import com.yaj.jaso.global.PageVo;

public class ProjectTeamsVo extends ProjectTeamsPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
