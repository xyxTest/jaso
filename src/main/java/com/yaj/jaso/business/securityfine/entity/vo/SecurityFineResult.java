package com.yaj.jaso.business.securityfine.entity.vo;

import java.util.List;

public class SecurityFineResult{
	private Object securityFine;
	private String cause;//事由
	private List<String> aboutUserRealNameList;
	private List<String> aboutDepartmentNameList;
	public List<String> getAboutDepartmentNameList() {
		return aboutDepartmentNameList;
	}
	public void setAboutDepartmentNameList(List<String> aboutDepartmentNameList) {
		this.aboutDepartmentNameList = aboutDepartmentNameList;
	}
	public List<String> getAboutUserRealNameList() {
		return aboutUserRealNameList;
	}
	public void setAboutUserRealNameList(List<String> aboutUserRealNameList) {
		this.aboutUserRealNameList = aboutUserRealNameList;
	}
	public Object getSecurityFine() {
		return securityFine;
	}
	public void setSecurityFine(Object securityFine) {
		this.securityFine = securityFine;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
