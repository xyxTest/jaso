package com.yaj.jaso.business.securityfine.entity.vo;

import java.util.List;
import com.yaj.jaso.business.fineabout.entity.po.FineAboutPO;
import com.yaj.jaso.business.securityfine.entity.po.SecurityFinePO;
public class SecurityFineAdd extends SecurityFinePO{

	private List<FineAboutPO> aboutIds;
	public List<FineAboutPO> getAboutIds() {
		return aboutIds;
	}
	public void setAboutIds(List<FineAboutPO> aboutIds) {
		this.aboutIds = aboutIds;
	}
}
