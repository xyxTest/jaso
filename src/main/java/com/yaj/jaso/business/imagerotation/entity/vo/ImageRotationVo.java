package com.yaj.jaso.business.imagerotation.entity.vo;

import com.yaj.jaso.business.imagerotation.entity.po.ImageRotationPO;
import com.yaj.jaso.global.PageVo;

public class ImageRotationVo extends ImageRotationPO {
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
