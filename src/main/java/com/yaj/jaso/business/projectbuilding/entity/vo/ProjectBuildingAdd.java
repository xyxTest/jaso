package com.yaj.jaso.business.projectbuilding.entity.vo;

import java.util.List;

import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;

public class ProjectBuildingAdd extends ProjectBuildingPO{
	private List<String> apartmentName;//户型名称

	public List<String> getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(List<String> apartmentName) {
		this.apartmentName = apartmentName;
	}
	
}
