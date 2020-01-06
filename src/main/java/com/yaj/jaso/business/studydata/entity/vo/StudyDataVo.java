package com.yaj.jaso.business.studydata.entity.vo;

import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;
import com.yaj.jaso.global.PageVo;

public class StudyDataVo extends StudyDataPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
