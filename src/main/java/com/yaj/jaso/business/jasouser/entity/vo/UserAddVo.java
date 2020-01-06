package com.yaj.jaso.business.jasouser.entity.vo;

import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

public class UserAddVo extends JasoUserPO{
	private Long[] workTypeId;
	private Long[] roleId;
	private Long[] departmentTree;
	private Long[] projectIdList;

	public Long[] getRoleId() {
		return roleId;
	}

	public void setRoleId(Long[] roleId) {
		this.roleId = roleId;
	}

	public Long[] getDepartmentTree() {
		return departmentTree;
	}

	public void setDepartmentTree(Long[] departmentTree) {
		this.departmentTree = departmentTree;
	}

	public Long[] getProjectIdList() {
		return projectIdList;
	}

	public void setProjectIdList(Long[] projectIdList) {
		this.projectIdList = projectIdList;
	}

	public Long[] getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long[] workTypeId) {
		this.workTypeId = workTypeId;
	}

	
	
}
