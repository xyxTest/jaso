package com.yaj.jaso.business.department.entity.vo;

import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.global.PageVo;

public class DepartmentVo extends DepartmentPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
