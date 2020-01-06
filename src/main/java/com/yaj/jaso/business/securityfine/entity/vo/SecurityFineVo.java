package com.yaj.jaso.business.securityfine.entity.vo;

import com.yaj.jaso.business.securityfine.entity.po.SecurityFinePO;
import com.yaj.jaso.global.PageVo;

public class SecurityFineVo extends SecurityFinePO {
	private String securityCheckName;
	private PageVo pageVo;

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public String getSecurityCheckName() {
		return securityCheckName;
	}

	public void setSecurityCheckName(String securityCheckName) {
		this.securityCheckName = securityCheckName;
	}
}
