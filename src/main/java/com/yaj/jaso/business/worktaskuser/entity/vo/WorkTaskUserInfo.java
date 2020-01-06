package com.yaj.jaso.business.worktaskuser.entity.vo;

import com.yaj.jaso.business.worktaskuser.entity.po.WorkTaskUserPO;

public class WorkTaskUserInfo extends WorkTaskUserPO{
	private String userRealName;
	private String userIcon;
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
}
