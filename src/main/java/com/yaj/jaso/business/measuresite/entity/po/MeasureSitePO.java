package com.yaj.jaso.business.measuresite.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-26
 */
@TableName(value = "measure_site")
public class MeasureSitePO {
    /*
    *
    */
    @TableId
    private Long measureSiteId;
    private Long projectBuildingId;
    /*专业类型*/
    private Integer majorType;
    /*
     * 级别类型（1：楼栋 、2：楼层、3：房间号）
     */
    private Integer siteType;
    /*
     * pid
     */
    private Long pid;
    /*
    *
    */
    private String measureSiteName;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /**
     *楼栋户型Id 
     */
    private Long apartmentId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long jasoUserId;
    /*
     *（1、选中、0、未选中 ）默认未选中
     */
    private Integer checkedStatus;
    /*
     *0、自身新增 1、来自项目设置 
     */
    private Integer fromProjectBuilding;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setMeasureSiteId(Long measureSiteId) {
        this.measureSiteId = measureSiteId;
    }

    public Long getMeasureSiteId() {
        return this.measureSiteId;
    }

    public void setMeasureSiteName(String measureSiteName) {
        this.measureSiteName = measureSiteName;
    }

    public String getMeasureSiteName() {
        return this.measureSiteName;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Integer getSiteType() {
		return siteType;
	}

	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getMajorType() {
		return majorType;
	}

	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}

	public Integer getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(Integer checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public Long getProjectBuildingId() {
		return projectBuildingId;
	}

	public void setProjectBuildingId(Long projectBuildingId) {
		this.projectBuildingId = projectBuildingId;
	}

	public Integer getFromProjectBuilding() {
		return fromProjectBuilding;
	}

	public void setFromProjectBuilding(Integer fromProjectBuilding) {
		this.fromProjectBuilding = fromProjectBuilding;
	}

	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}

}