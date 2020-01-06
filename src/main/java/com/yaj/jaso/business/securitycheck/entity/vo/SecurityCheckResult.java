package com.yaj.jaso.business.securitycheck.entity.vo;

import java.util.List;

import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;

public class SecurityCheckResult{
	private Object securityCheck; 
	private List<AboutUserList> aboutUserList;
    private List<QualityCheckTypePO> checkTypeList;
 	public Object getSecurityCheck() {
		return securityCheck;
	}

	public void setSecurityCheck(Object securityCheck) {
		this.securityCheck = securityCheck;
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
	
}
