package com.yaj.jaso.business.jasouser.entity.vo;

import java.util.List;

public class UserInfoDetail{
    private String userRealName;
    private String userName;
    private Long jasoUserId;
    private String userIcon;
    private Long companyId;
    private Integer isAdmin;
    private List<Long> menuList;
    private List<ProjectList> projectList;
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getJasoUserId() {
		return jasoUserId;
	}
	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public List<ProjectList> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectList> projectList) {
		this.projectList = projectList;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<Long> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Long> menuList) {
		this.menuList = menuList;
	}
    
	
}
