package com.yaj.jaso.business.constructlog.entity.vo;

import java.util.Date;

public class ConstructLogCount {
	private Long projectId;
	private Date startTime;
	private Date endTime;
	private Long projectTendersId;
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
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
	public Long getProjectTendersId() {
		return projectTendersId;
	}
	public void setProjectTendersId(Long projectTendersId) {
		this.projectTendersId = projectTendersId;
	}
	
}
