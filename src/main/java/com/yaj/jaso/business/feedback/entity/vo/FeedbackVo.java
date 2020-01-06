package com.yaj.jaso.business.feedback.entity.vo;

import com.yaj.jaso.business.feedback.entity.po.FeedbackPO;
import com.yaj.jaso.global.PageVo;

public class FeedbackVo extends FeedbackPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
