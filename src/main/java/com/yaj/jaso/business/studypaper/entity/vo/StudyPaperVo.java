package com.yaj.jaso.business.studypaper.entity.vo;

import com.yaj.jaso.business.studypaper.entity.po.StudyPaperPO;
import com.yaj.jaso.global.PageVo;

public class StudyPaperVo extends StudyPaperPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
