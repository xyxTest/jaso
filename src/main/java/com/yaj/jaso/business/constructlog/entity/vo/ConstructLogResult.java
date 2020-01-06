package com.yaj.jaso.business.constructlog.entity.vo;

import java.util.List;
import java.util.Map;
import com.yaj.jaso.business.constructlogcontent.entity.po.ConstructLogContentPO;

public class ConstructLogResult {
	private Map<String,Object> constructLog;
	private List<ConstructLogContentPO> contentList;
	public Map<String, Object> getConstructLog() {
		return constructLog;
	}
	public void setConstructLog(Map<String, Object> constructLog) {
		this.constructLog = constructLog;
	}
	public List<ConstructLogContentPO> getContentList() {
		return contentList;
	}
	public void setContentList(List<ConstructLogContentPO> contentList) {
		this.contentList = contentList;
	}
	
	
}
