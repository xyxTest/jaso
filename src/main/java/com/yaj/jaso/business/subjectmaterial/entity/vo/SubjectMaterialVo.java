package com.yaj.jaso.business.subjectmaterial.entity.vo;

import com.yaj.jaso.business.subjectmaterial.entity.po.SubjectMaterialPO;
import com.yaj.jaso.global.PageVo;

public class SubjectMaterialVo extends SubjectMaterialPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
