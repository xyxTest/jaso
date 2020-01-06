package com.yaj.jaso.business.measuresite.entity.vo;

import java.util.List;

import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;

public class MeasureSiteAddVo{
	private Long measurePaperId;
	private List<ProjectBuildingPO> projectBuildingList;
	private Long measureBuildingId;
	public Long getMeasurePaperId() {
		return measurePaperId;
	}

	public void setMeasurePaperId(Long measurePaperId) {
		this.measurePaperId = measurePaperId;
	}

	public List<ProjectBuildingPO> getProjectBuildingList() {
		return projectBuildingList;
	}

	public void setProjectBuildingList(List<ProjectBuildingPO> projectBuildingList) {
		this.projectBuildingList = projectBuildingList;
	}

	public Long getMeasureBuildingId() {
		return measureBuildingId;
	}

	public void setMeasureBuildingId(Long measureBuildingId) {
		this.measureBuildingId = measureBuildingId;
	}

	
}
