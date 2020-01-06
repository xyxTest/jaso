package com.yaj.jaso.business.measurescreenlog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-29
 */
@TableName(value = "measure_screen_log")
public class MeasureScreenLogPO {
    /*
    *
    */
    @TableId
    private Long measureScreenLogId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
     *名字 
     */
    private String measureScreenLogName;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *楼栋名称
    */
    private Long measureSiteId;
    /*
    *
    */
    private String projectCheckTypeId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setMeasureScreenLogId(Long measureScreenLogId) {
        this.measureScreenLogId = measureScreenLogId;
    }

    public Long getMeasureScreenLogId() {
        return this.measureScreenLogId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setMeasureSiteId(Long measureSiteId) {
        this.measureSiteId = measureSiteId;
    }

    public Long getMeasureSiteId() {
        return this.measureSiteId;
    }

    public void setProjectCheckTypeId(String projectCheckTypeId) {
        this.projectCheckTypeId = projectCheckTypeId;
    }

    public String getProjectCheckTypeId() {
        return this.projectCheckTypeId;
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

	public String getMeasureScreenLogName() {
		return measureScreenLogName;
	}

	public void setMeasureScreenLogName(String measureScreenLogName) {
		this.measureScreenLogName = measureScreenLogName;
	}

}