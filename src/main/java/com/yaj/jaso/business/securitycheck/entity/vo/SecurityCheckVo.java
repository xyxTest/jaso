package com.yaj.jaso.business.securitycheck.entity.vo;

import java.util.Date;
import java.util.List;

import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;
import com.yaj.jaso.global.PageVo;

public class SecurityCheckVo extends SecurityCheckPO {
	private PageVo pageVo;
	private Date start;
	private Date end;
	private List<Long> ids;
	private String searchContent;//名称、检查人
	private Integer isCheck;//1、检查单 2、整改单
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

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
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
