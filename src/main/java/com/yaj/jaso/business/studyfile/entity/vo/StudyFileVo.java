package com.yaj.jaso.business.studyfile.entity.vo;

import com.yaj.jaso.business.studyfile.entity.po.StudyFilePO;
import com.yaj.jaso.global.PageVo;

public class StudyFileVo extends StudyFilePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
