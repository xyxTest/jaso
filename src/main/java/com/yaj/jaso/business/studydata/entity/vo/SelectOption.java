package com.yaj.jaso.business.studydata.entity.vo;

import java.util.List;

import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;

public class SelectOption extends StudyDataPO{
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
