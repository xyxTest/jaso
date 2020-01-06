package com.yaj.jaso.business.qualityfine.entity.vo;

import com.yaj.jaso.business.qualityfine.entity.po.QualityFinePO;
import com.yaj.jaso.global.PageVo;

public class QualityFineVo extends QualityFinePO {
	private String qualityCheckName;
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public String getQualityCheckName() {
		return qualityCheckName;
	}

	public void setQualityCheckName(String qualityCheckName) {
		this.qualityCheckName = qualityCheckName;
	}
}
