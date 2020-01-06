package com.yaj.jaso.business.measuresite.entity.po;

import java.util.List;

import com.yaj.jaso.global.PageVo;

public class CountByPageMeasure {
	private List<GetsResultMeasure> resultList;
	private PageVo pageVo;
	public List<GetsResultMeasure> getResultList() {
		return resultList;
	}
	public void setResultList(List<GetsResultMeasure> resultList) {
		this.resultList = resultList;
	}
	public PageVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
}
