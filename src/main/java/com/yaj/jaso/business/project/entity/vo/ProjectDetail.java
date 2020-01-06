package com.yaj.jaso.business.project.entity.vo;

import java.util.List;

import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;

public class ProjectDetail extends ProjectPO{
	private String leader;
	private List<ProjectTendersPO> projectTenders ;

	public List<ProjectTendersPO> getProjectTenders() {
		return projectTenders;
	}

	public void setProjectTenders(List<ProjectTendersPO> projectTenders) {
		this.projectTenders = projectTenders;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
}
