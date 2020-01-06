package com.yaj.jaso.business.securitycheck.entity.vo;


import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;

public class SecurityCheckGet extends SecurityCheckPO {
	private String userRealName;
	private String projectPaperUrl;
	private String natureName;
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getProjectPaperUrl() {
		return projectPaperUrl;
	}

	public void setProjectPaperUrl(String projectPaperUrl) {
		this.projectPaperUrl = projectPaperUrl;
	}

	public String getNatureName() {
		return natureName;
	}

	public void setNatureName(String natureName) {
		this.natureName = natureName;
	}
}
