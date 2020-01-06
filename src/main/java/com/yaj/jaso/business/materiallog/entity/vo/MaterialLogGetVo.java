package com.yaj.jaso.business.materiallog.entity.vo;

import java.util.Date;
import java.util.List;

import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;

public class MaterialLogGetVo{
	private Long jasoUserId;//领取人id
	private Long projectId;
	private Long companyId;
	private String fromWhere;
	private String remark;
	private Date createTime;
	private Integer logType;//0、出库 1、入库
	private List<MaterialLogPO> materialLogs;
	public Long getJasoUserId() {
		return jasoUserId;
	}
	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<MaterialLogPO> getMaterialLogs() {
		return materialLogs;
	}
	public void setMaterialLogs(List<MaterialLogPO> materialLogs) {
		this.materialLogs = materialLogs;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	
	
}
