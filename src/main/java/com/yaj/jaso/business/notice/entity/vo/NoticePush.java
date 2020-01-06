package com.yaj.jaso.business.notice.entity.vo;

import java.util.List;

import com.yaj.jaso.business.notice.entity.po.NoticePO;

public class NoticePush extends NoticePO{
	private List<Long> userIdList;

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}
}
