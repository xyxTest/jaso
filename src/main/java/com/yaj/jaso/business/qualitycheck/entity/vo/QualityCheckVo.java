package com.yaj.jaso.business.qualitycheck.entity.vo;

import java.util.Date;
import java.util.List;

import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;
import com.yaj.jaso.global.PageVo;

public class QualityCheckVo extends QualityCheckPO {
	private PageVo pageVo;
	private Date start;
	private Date end;
	private Integer isCheck;//1、检查单 2、整改单
	private List<Long> ids;
	private String seachContent;
	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getSeachContent() {
		return seachContent;
	}

	public void setSeachContent(String seachContent) {
		this.seachContent = seachContent;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}
