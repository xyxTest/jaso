package com.yaj.jaso.business.materialimportlog.entity.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

public class MaterialImportLogPO {
	 /*
    *
    */
    @TableId
    private Long materialImportLogId;
    /*
     * 
     */
    private Long projectId;
    /*
     * 
     */
    private String importFileUrl;
    /*
     * 
     */
    private Long companyId;
    /*
    *
    */
    private Long createUser;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
	public Long getMaterialImportLogId() {
		return materialImportLogId;
	}
	public void setMaterialImportLogId(Long materialImportLogId) {
		this.materialImportLogId = materialImportLogId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getImportFileUrl() {
		return importFileUrl;
	}
	public void setImportFileUrl(String importFileUrl) {
		this.importFileUrl = importFileUrl;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIfDelete() {
		return ifDelete;
	}
	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
    
}
