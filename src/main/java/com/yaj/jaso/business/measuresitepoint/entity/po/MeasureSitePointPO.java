package com.yaj.jaso.business.measuresitepoint.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;


/*
 * @Description: 
 * @date: 2019-08-28
 */
@TableName(value = "measure_site_point")
public class MeasureSitePointPO {
    /*
    *
    */
	@TableId
    private Long measureSitePointId;
    /*
    *测点id
    */
    private Long measurePointId;
    /*
    *检查项id
    */
    private Long projectCheckTypeId;
    /*
    *房间号id
    */
    private Long measureSiteId;
    /*
     *项目id 
     */
    private Long projectId;
    /**
     * 
     */
    private Long companyId;
    /*
     * 类型 (1、楼栋 2、楼层 3、 房间)
     */
    private Integer siteType;
    /*
     *1、正常  2、爆点 3、整改完成后的爆点
     */
    private Integer status;
    /*
     *1、在图纸内设置的（默认） 2、房间设置的 
     */
     private Integer pointType;
     @TableLogic
     private Integer ifDelete;
     
    public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	public void setMeasureSitePointId(Long measureSitePointId) {
        this.measureSitePointId = measureSitePointId;
    }

    public Long getMeasureSitePointId() {
        return this.measureSitePointId;
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

    public void setMeasureSiteId(Long measureSiteId) {
        this.measureSiteId = measureSiteId;
    }

    public Long getMeasureSiteId() {
        return this.measureSiteId;
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

	public Integer getSiteType() {
		return siteType;
	}

	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}


}