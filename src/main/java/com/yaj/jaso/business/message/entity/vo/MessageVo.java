package com.yaj.jaso.business.message.entity.vo;

import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.global.PageVo;

public class MessageVo extends MessagePO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
