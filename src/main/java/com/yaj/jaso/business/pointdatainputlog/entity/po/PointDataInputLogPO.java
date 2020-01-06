package com.yaj.jaso.business.pointdatainputlog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@TableName(value = "point_data_input_log")
public class PointDataInputLogPO {
    /*
    *
    */
    @TableId
    private Long pointDataInputLogId;
    /*
    *测点id
    */
    private Long measurePointId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
     * 录入测点的具体位置
     */
    private String measureSiteInfo;
    /*
    *检查项id
    */
    private Long projectCheckTypeId;
    /*
    *测量数据
    */
    private Integer inputData;
    /*
    *测点序号
    */
    private Integer label;
    /*
    *(1、正常点位 2、问题点位)
    */
    private Integer pointStatus;
    /*
    *区域id
    */
    private Long measureSiteId;
    /*
    *
    */
    private String remark;
    /*
    *测量人id
    */
    private Long jasoUserId;
    /*
    *测量时间
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setPointDataInputLogId(Long pointDataInputLogId) {
        this.pointDataInputLogId = pointDataInputLogId;
    }

    public Long getPointDataInputLogId() {
        return this.pointDataInputLogId;
    }

    public void setMeasurePointId(Long measurePointId) {
        this.measurePointId = measurePointId;
    }

    public Long getMeasurePointId() {
        return this.measurePointId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectCheckTypeId(Long projectCheckTypeId) {
        this.projectCheckTypeId = projectCheckTypeId;
    }

    public Long getProjectCheckTypeId() {
        return this.projectCheckTypeId;
    }

    public void setInputData(Integer inputData) {
        this.inputData = inputData;
    }

    public Integer getInputData() {
        return this.inputData;
    }

    public void setMeasureSiteId(Long measureSiteId) {
        this.measureSiteId = measureSiteId;
    }

    public Long getMeasureSiteId() {
        return this.measureSiteId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public String getMeasureSiteInfo() {
		return measureSiteInfo;
	}

	public void setMeasureSiteInfo(String measureSiteInfo) {
		this.measureSiteInfo = measureSiteInfo;
	}

	public Integer getPointStatus() {
		return pointStatus;
	}

	public void setPointStatus(Integer pointStatus) {
		this.pointStatus = pointStatus;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

}