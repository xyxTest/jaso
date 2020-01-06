package com.yaj.jaso.business.studyworkertype.entity.vo;

import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.global.PageVo;

public class StudyWorkerTypeVo extends StudyWorkerTypePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
