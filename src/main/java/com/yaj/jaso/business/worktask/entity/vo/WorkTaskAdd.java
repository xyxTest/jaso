package com.yaj.jaso.business.worktask.entity.vo;

import java.util.List;

import com.yaj.jaso.business.worktask.entity.po.WorkTaskPO;
import com.yaj.jaso.business.worktaskuser.entity.po.WorkTaskUserPO;

public class WorkTaskAdd extends WorkTaskPO{
	private List<WorkTaskUserPO> workTaskUserList;

	public List<WorkTaskUserPO> getWorkTaskUserList() {
		return workTaskUserList;
	}

	public void setWorkTaskUserList(List<WorkTaskUserPO> workTaskUserList) {
		this.workTaskUserList = workTaskUserList;
	}
	
}
