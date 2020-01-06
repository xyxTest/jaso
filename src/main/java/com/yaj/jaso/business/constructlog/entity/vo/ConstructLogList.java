package com.yaj.jaso.business.constructlog.entity.vo;

import java.util.List;

import com.yaj.jaso.business.constructlog.entity.po.ConstructLogPO;
import com.yaj.jaso.business.constructlogcontent.entity.po.ConstructLogContentPO;

public class ConstructLogList {
	private ConstructLogPO constructLog;
	private List<ConstructLogContentPO> contentList;

	public List<ConstructLogContentPO> getContentList() {
		return contentList;
	}

	public void setContentList(List<ConstructLogContentPO> contentList) {
		this.contentList = contentList;
	}

	public ConstructLogPO getConstructLog() {
		return constructLog;
	}

	public void setConstructLog(ConstructLogPO constructLog) {
		this.constructLog = constructLog;
	}
	
}
