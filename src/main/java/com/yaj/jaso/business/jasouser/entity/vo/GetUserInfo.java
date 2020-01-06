package com.yaj.jaso.business.jasouser.entity.vo;

import java.util.List;

import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.menu.entity.po.MenuPO;

public class GetUserInfo extends JasoUserPO{
	private List<MenuPO> menuList;
	private Integer isAdmin;
	public List<MenuPO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuPO> menuList) {
		this.menuList = menuList;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	
}
