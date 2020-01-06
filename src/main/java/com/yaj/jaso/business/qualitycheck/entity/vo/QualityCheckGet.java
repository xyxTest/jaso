package com.yaj.jaso.business.qualitycheck.entity.vo;


import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;

public class QualityCheckGet extends QualityCheckPO {
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
