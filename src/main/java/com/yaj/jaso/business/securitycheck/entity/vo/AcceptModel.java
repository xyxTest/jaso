package com.yaj.jaso.business.securitycheck.entity.vo;

import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;

public class AcceptModel extends SecurityCheckPO{
	private Integer isAccept;//1、接受 2、拒绝
	
	public Integer getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}
	
	
}
