package com.yaj.jaso.business.studyfile.entity.vo;

import java.util.List;

import com.yaj.jaso.business.studyfile.entity.po.StudyFilePO;

public class StudyFileAdd extends StudyFilePO{
	private List<Long> workTypeIdList;

	public List<Long> getWorkTypeIdList() {
		return workTypeIdList;
	}

	public void setWorkTypeIdList(List<Long> workTypeIdList) {
		this.workTypeIdList = workTypeIdList;
	}
}
