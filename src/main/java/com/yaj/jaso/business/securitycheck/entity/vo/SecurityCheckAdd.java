package com.yaj.jaso.business.securitycheck.entity.vo;

import java.util.List;

import com.yaj.jaso.business.qualitycheck.entity.vo.AboutUserList;
import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;
import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;

public class SecurityCheckAdd extends SecurityCheckPO {
	private List<QualityCheckTypePO> checkTypeList;
	private List<AboutUserList> informUserList;
	public List<QualityCheckTypePO> getCheckTypeList() {
		return checkTypeList;
	}

	public void setCheckTypeList(List<QualityCheckTypePO> checkTypeList) {
		this.checkTypeList = checkTypeList;
	}

	public List<AboutUserList> getInformUserList() {
		return informUserList;
	}

	public void setInformUserList(List<AboutUserList> informUserList) {
		this.informUserList = informUserList;
	}
}
