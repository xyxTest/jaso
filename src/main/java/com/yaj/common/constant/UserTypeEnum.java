package com.yaj.common.constant;


/**
* @Description: 系统用户类型
* @author Orange
* @date 2018年1月28日
*
*/
public enum UserTypeEnum {
	WEB(1), //PC用户
	APP(2);//小程序用户

	private Integer type;

	private UserTypeEnum(Integer type) {
		this.setType(type);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
