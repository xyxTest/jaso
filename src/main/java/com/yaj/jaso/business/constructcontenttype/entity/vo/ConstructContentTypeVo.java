package com.yaj.jaso.business.constructcontenttype.entity.vo;

import com.yaj.jaso.business.constructcontenttype.entity.po.ConstructContentTypePO;
import com.yaj.jaso.global.PageVo;

public class ConstructContentTypeVo extends ConstructContentTypePO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
