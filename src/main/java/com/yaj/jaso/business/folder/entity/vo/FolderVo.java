package com.yaj.jaso.business.folder.entity.vo;

import com.yaj.jaso.business.folder.entity.po.FolderPO;
import com.yaj.jaso.global.PageVo;

public class FolderVo extends FolderPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
