package com.yaj.jaso.business.projectchecktype.entity.vo;

import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.global.PageVo;

public class ProjectCheckTypeVo extends ProjectCheckTypePO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
