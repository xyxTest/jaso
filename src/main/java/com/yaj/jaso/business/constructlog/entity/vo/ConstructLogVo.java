package com.yaj.jaso.business.constructlog.entity.vo;

import java.util.Date;
import java.util.List;

import com.yaj.jaso.business.constructlog.entity.po.ConstructLogPO;
import com.yaj.jaso.business.constructlogcontent.entity.po.ConstructLogContentPO;
import com.yaj.jaso.global.PageVo;

public class ConstructLogVo extends ConstructLogPO {
	private PageVo pageVo;
	private List<ConstructLogContentPO> contentList;
	private Date startTime;
	private Date endTime;
	private String createUserName;//创建人姓名搜索
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<ConstructLogContentPO> getContentList() {
		return contentList;
	}

	public void setContentList(List<ConstructLogContentPO> contentList) {
		this.contentList = contentList;
	}
	
	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
}
