package com.yaj.jaso.business.projectbuilding.entity.po;

import java.util.List;

import com.yaj.jaso.global.PageVo;

public class CountByPage {
	private List<GetsResult> resultList;
	private PageVo pageVo;
	public List<GetsResult> getResultList() {
		return resultList;
	}
	public void setResultList(List<GetsResult> resultList) {
		this.resultList = resultList;
	}
	public PageVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
