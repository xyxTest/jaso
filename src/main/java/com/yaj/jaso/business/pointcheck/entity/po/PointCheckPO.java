package com.yaj.jaso.business.pointcheck.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@TableName(value = "point_check")
public class PointCheckPO {
    /*
    *
    */
    @TableId
    private Long pointCheckId;
    /*
    *
    */
    private Long measurePointId;
    /*
    *
    */
    private Long projectCheckTypeId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
    *设计值（标准值）
    */
    private Integer standardNum;
    /*
    *误差上限
    */
    private Integer errorUpperLimit;
    /*
    *误差下限
    */
    private Integer errorLowerLimit;
    /*
    *
    */
    private Long measureSiteId;
    private Long jasoUserId;
    /*
    *0、否 1、是现场新增的检查项
    */
    private Integer isAdd;//
    /**
     *app使用变量 
     */
    private Integer isSave;
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setPointCheckId(Long pointCheckId) {
        this.pointCheckId = pointCheckId;
    }

    public Long getPointCheckId() {
        return this.pointCheckId;
    }

    public void setMeasurePointId(Long measurePointId) {
        this.measurePointId = measurePointId;
    }

    public Long getMeasurePointId() {
        return this.measurePointId;
    }

    public void setProjectCheckTypeId(Long projectCheckTypeId) {
        this.projectCheckTypeId = projectCheckTypeId;
    }

    public Long getProjectCheckTypeId() {
        return this.projectCheckTypeId;
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

    public void setStandardNum(Integer standardNum) {
        this.standardNum = standardNum;
    }

    public Integer getStandardNum() {
        return this.standardNum;
    }

    public void setErrorUpperLimit(Integer errorUpperLimit) {
        this.errorUpperLimit = errorUpperLimit;
    }

    public Integer getErrorUpperLimit() {
        return this.errorUpperLimit;
    }

    public void setErrorLowerLimit(Integer errorLowerLimit) {
        this.errorLowerLimit = errorLowerLimit;
    }

    public Integer getErrorLowerLimit() {
        return this.errorLowerLimit;
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

	public Integer getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	public Long getMeasureSiteId() {
		return measureSiteId;
	}

	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}

	public Integer getIsSave() {
		return isSave;
	}

	public void setIsSave(Integer isSave) {
		this.isSave = isSave;
	}

}