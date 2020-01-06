package com.yaj.jaso.business.securitycheck.entity.vo;

public class AboutUserList {
	private Long jasoUserId;
	private String userRealName;
	private Integer userType;
	private String userIcon;
	public Long getJasoUserId() {
		return jasoUserId;
	}
	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
}
