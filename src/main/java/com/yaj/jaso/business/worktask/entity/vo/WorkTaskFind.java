package com.yaj.jaso.business.worktask.entity.vo;

import java.util.Date;
import java.util.List;

import com.yaj.jaso.business.worktask.entity.po.WorkTaskPO;

public class WorkTaskFind extends WorkTaskPO{
	private Date finishedDateStart;
	private Date finishedDateEnd;
	private Date beignDateStart;
	private Date beginDateEnd;
	private List<Integer> statusList;
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
