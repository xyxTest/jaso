package com.yaj.jaso.business.materiallog.entity.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

/*
 * @Description: 
 * @date: 2019-08-05
 */
@TableName(value = "material_log")
public class MaterialLogPO {
	/*
    *
    */
    @TableId
    private Long materialLogId;
    /*
     * 
     */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
     * 
     */
    private Long materialId;
    /**
     * 
     */
    private Long materialLogListId;
    /*
     *(0、入库 1、出库)
     */
    private Integer logType;
    /*
     * 价格
     **/
    private Double materialPrice;
    /*
     * 每一次的出、入库数量
     */
    private Integer logNum;

    /*
     *操作人 
     */
    private Long jasoUserId;
    /*
     * 物资来源
     */
    private String materialFrom;
    /*
    *备注
    */
    private String remark;
    /*
     * 录入日期
     */
    private Date inputDate;
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
	public Long getMaterialLogId() {
		return materialLogId;
	}
	public void setMaterialLogId(Long materialLogId) {
		this.materialLogId = materialLogId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	
	public Integer getLogNum() {
		return logNum;
	}
	public void setLogNum(Integer logNum) {
		this.logNum = logNum;
	}
	
	public String getMaterialFrom() {
		return materialFrom;
	}
	public void setMaterialFrom(String materialFrom) {
		this.materialFrom = materialFrom;
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
	public Integer getIfDelete() {
		return ifDelete;
	}
	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
	public Long getJasoUserId() {
		return jasoUserId;
	}
	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	public Long getMaterialLogListId() {
		return materialLogListId;
	}
	public void setMaterialLogListId(Long materialLogListId) {
		this.materialLogListId = materialLogListId;
	}
	public Double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
    
}
