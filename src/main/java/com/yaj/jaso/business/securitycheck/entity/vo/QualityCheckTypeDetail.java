package com.yaj.jaso.business.securitycheck.entity.vo;

import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;

public class QualityCheckTypeDetail extends QualityCheckTypePO{
	private String checkName;

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
}
