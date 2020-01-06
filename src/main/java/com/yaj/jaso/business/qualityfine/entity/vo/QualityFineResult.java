package com.yaj.jaso.business.qualityfine.entity.vo;

import java.util.List;

public class QualityFineResult{
	private Object qualityFine;
	private String Cause;
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
	public Object getQualityFine() {
		return qualityFine;
	}
	public void setQualityFine(Object qualityFine) {
		this.qualityFine = qualityFine;
	}
	public String getCause() {
		return Cause;
	}
	public void setCause(String cause) {
		Cause = cause;
	}
	
	
}
