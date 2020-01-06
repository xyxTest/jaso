package com.yaj.jaso.business.reply.entity.vo;

import com.yaj.jaso.business.reply.entity.po.ReplyPO;
import com.yaj.jaso.global.PageVo;

public class ReplyVo extends ReplyPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
