package com.yaj.jaso.business.newsinfo.entity.vo;

import com.yaj.jaso.business.newsinfo.entity.po.NewsInfoPO;
import com.yaj.jaso.global.PageVo;

public class NewsInfoVo extends NewsInfoPO{
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
