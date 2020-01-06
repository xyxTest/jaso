package com.yaj.jaso.business.worktask.entity.vo;

import java.util.Date;
import java.util.List;

public class WorkAllOptions {
	private Integer type;//1、负责的 2、分派的 3、参与的
	private Long projectId;
	private List<Integer> statusList;//1、待指派 2、待接受 3、进行时 4、待验收 5、已完成
	private Date finishedDateStart;
	private Date finishedDateEnd;
	private Date beignDateStart;
	private Date beginDateEnd;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Date getFinishedDateStart() {
		return finishedDateStart;
	}
	public void setFinishedDateStart(Date finishedDateStart) {
		this.finishedDateStart = finishedDateStart;
	}
	public Date getFinishedDateEnd() {
		return finishedDateEnd;
	}
	public void setFinishedDateEnd(Date finishedDateEnd) {
		this.finishedDateEnd = finishedDateEnd;
	}
	public Date getBeignDateStart() {
		return beignDateStart;
	}
	public void setBeignDateStart(Date beignDateStart) {
		this.beignDateStart = beignDateStart;
	}
	public Date getBeginDateEnd() {
		return beginDateEnd;
	}
	public void setBeginDateEnd(Date beginDateEnd) {
		this.beginDateEnd = beginDateEnd;
	}
	public List<Integer> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}
	
}
