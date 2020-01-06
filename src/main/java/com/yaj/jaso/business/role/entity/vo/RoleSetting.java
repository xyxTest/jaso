package com.yaj.jaso.business.role.entity.vo;

import java.util.List;

public class RoleSetting {
	private Long roleId;
	private List<String> menuId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<String> getMenuId() {
		return menuId;
	}
	public void setMenuId(List<String> menuId) {
		this.menuId = menuId;
	}
	
}
