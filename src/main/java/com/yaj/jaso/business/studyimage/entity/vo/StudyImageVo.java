package com.yaj.jaso.business.studyimage.entity.vo;

import com.yaj.jaso.business.studyimage.entity.po.StudyImagePO;
import com.yaj.jaso.global.PageVo;

public class StudyImageVo extends StudyImagePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
