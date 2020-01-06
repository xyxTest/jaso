package com.yaj.jaso.business.securitycheck.entity.vo;

import java.util.List;

public class SecurityCheckDetail {
	private List<QualityCheckTypeDetail> checkTypeList;

	public List<QualityCheckTypeDetail> getCheckTypeList() {
		return checkTypeList;
	}

	public void setCheckTypeList(List<QualityCheckTypeDetail> checkTypeList) {
		this.checkTypeList = checkTypeList;
	}
}
