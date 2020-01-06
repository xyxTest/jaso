package com.yaj.jaso.business.pointdatainputlog.entity.vo;

import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.global.PageVo;

public class PointDataInputLogVo extends PointDataInputLogPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
