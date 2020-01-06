package com.yaj.jaso.business.menu.entity.vo;

public class MenuTree {
	private Long menuId;
	private String menuPcName;
	private Long pid;
	private Integer ifDelete;
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getMenuPcName() {
		return menuPcName;
	}
	public void setMenuPcName(String menuPcName) {
		this.menuPcName = menuPcName;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getIfDelete() {
		return ifDelete;
	}
	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
	
}
