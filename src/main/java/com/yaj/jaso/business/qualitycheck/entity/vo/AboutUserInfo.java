package com.yaj.jaso.business.qualitycheck.entity.vo;

import com.yaj.jaso.business.qualitycheckusers.entity.po.QualityCheckUsersPO;

public class AboutUserInfo extends QualityCheckUsersPO{
	private String userIcon;

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
}
