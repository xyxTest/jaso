package com.yaj.jaso.business.worktype.entity.vo;

import java.util.List;

import com.yaj.jaso.business.worktype.entity.po.WorkTypePO;

public class GetByRoles extends WorkTypePO{
	private List<Long> roleIdList;

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
}
