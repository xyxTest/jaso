package com.yaj.jaso.business.projectbuilding.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-07
 */
@TableName(value = "project_building")
public class ProjectBuildingPO {
    /*
    *
    */
    @TableId
    private Long projectBuildingId;
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
    private String buildingName;
    /**
     *楼栋描述 --该栋楼层数量(编辑时必传)
     */
    private Integer floorNum;
    /**
     *楼栋描述 --每层房间数量(编辑时必传)
     */
    private Integer roomNum;
    /**
     *楼栋描述 --每栋户型数量(编辑时必传)
     */
    private Integer apartmentNum;
    /**
     *房间号对应的户型id 
     */
    private Long apartmentId;//户型id
    /*
     *等级 1、楼栋 2、楼层 3、房间号
     */
    private Integer status;
    /*
    *
    */
    private Long pid;
    /*
    *
    */
    private Date createTime;
    /**
     *更新时间 
     */
    private Date updateTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setProjectBuildingId(Long projectBuildingId) {
        this.projectBuildingId = projectBuildingId;
    }

    public Long getProjectBuildingId() {
        return this.projectBuildingId;
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

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getApartmentNum() {
		return apartmentNum;
	}

	public void setApartmentNum(Integer apartmentNum) {
		this.apartmentNum = apartmentNum;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}

}