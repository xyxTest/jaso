package com.yaj.jaso.business.qualitycheck.entity.vo;

import java.util.List;

import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;

public class QualityCheckResult{
	private Object qualityCheck; 
	private String userRealName;
	private Object pageInfo;
	private List<AboutUserList> aboutUserList;
	private List<QualityCheckTypePO> checkTypeList;
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	public Object getQualityCheck() {
		return qualityCheck;
	}

	public void setQualityCheck(Object qualityCheck) {
		this.qualityCheck = qualityCheck;
	}


	public List<AboutUserList> getAboutUserList() {
		return aboutUserList;
	}

	public void setAboutUserList(List<AboutUserList> aboutUserList) {
		this.aboutUserList = aboutUserList;
	}

	public List<QualityCheckTypePO> getCheckTypeList() {
		return checkTypeList;
	}

	public void setCheckTypeList(List<QualityCheckTypePO> checkTypeList) {
		this.checkTypeList = checkTypeList;
	}

	public Object getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Object pageInfo) {
		this.pageInfo = pageInfo;
	}

}
