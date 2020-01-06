package com.yaj.jaso.business.studyevent.entity.vo;

import com.yaj.jaso.business.studyevent.entity.po.StudyEventPO;
import com.yaj.jaso.global.PageVo;

public class StudyEventVo extends StudyEventPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
