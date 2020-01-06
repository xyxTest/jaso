package com.yaj.jaso.business.jasouser.entity.vo;

import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVo;

public class UserVo extends JasoUserPO {
	private PageVo page;
	private Long[][] departmentTree;

	

	public Long[][] getDepartmentTree() {
		return departmentTree;
	}

	public void setDepartmentTree(Long[][] departmentTree) {
		this.departmentTree = departmentTree;
	}

	public PageVo getPageVo() {
		return page;
	}

	public void setPageVo(PageVo pageVo) {
		this.page = pageVo;
	}
	
}
