package com.yaj.jaso.business.notice.entity.vo;

import com.yaj.jaso.business.notice.entity.po.NoticePO;
import com.yaj.jaso.global.PageVo;

public class NoticeVo extends NoticePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
