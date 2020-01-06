package com.yaj.jaso.business.attencelog.entity.vo;

import java.util.List;

import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

public class AttenceCount {
	private String date;
	private List<JasoUserPO> userList;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<JasoUserPO> getUserList() {
		return userList;
	}
	public void setUserList(List<JasoUserPO> userList) {
		this.userList = userList;
	}
	
}
