package com.yaj.jaso.business.measureproblem.entity.vo;

import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;

public class MeasureProblemResult extends MeasureProblemPO{
	private String userRealName;
	private String checkName;
	private String inputDatas;
	private String projectName;
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getInputDatas() {
		return inputDatas;
	}
	public void setInputDatas(String inputDatas) {
		this.inputDatas = inputDatas;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}		
}
