package com.yaj.jaso.business.qualitycheck.entity.vo;

import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;

public class AcceptModel extends QualityCheckPO{
	private Integer isAccept;//1、接受 2、拒绝
	
	public Integer getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}
	
	
}
