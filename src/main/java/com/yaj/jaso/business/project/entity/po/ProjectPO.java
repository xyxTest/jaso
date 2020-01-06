package com.yaj.jaso.business.project.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@TableName(value = "project")
public class ProjectPO {
    /*
    *
    */
    @TableId
    private Long projectId;
    /*
    *项目名称
    */
    private String projectName;
    /*
    *项目负责人
    */
    private Long jasoUserId;
    /*
    *项目图片
    */
    private String projectIcon;
    /*
    *建设单位
    */
    private String buildingUnit;
    /*
    *设计单位
    */
    private String designUnit;
    /*
    *施工单位
    */
    private String constructUnit;
    /*
    *项目描述
    */
    private String projectDescribe;
    /*
    *项目地址
    */
    private String projectAddress;
    /*模型id*/
    private String fileId;
    /*
    *
    */
    private Long companyId;
    /*
    *项目编码（结合项目所在地区的行政区编码组合）
    */
    private String projectCode;
    /*城市编码*/
    private String cityCode;
    /*
    *项目周期
    */
    private String projectCycle;
    /*
    *项目动工时间
    */
    private Date projectDate;
    private Date createTime;
    /*1、进行中 2、已竣工*/
    private Integer type;
    /*
    *
    */
    private Long createUser;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    

    public void setProjectIcon(String projectIcon) {
        this.projectIcon = projectIcon;
    }

    public String getProjectIcon() {
        return this.projectIcon;
    }

    public void setBuildingUnit(String buildingUnit) {
        this.buildingUnit = buildingUnit;
    }

    public String getBuildingUnit() {
        return this.buildingUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit;
    }

    public String getDesignUnit() {
        return this.designUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    public String getConstructUnit() {
        return this.constructUnit;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public String getProjectDescribe() {
        return this.projectDescribe;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectAddress() {
        return this.projectAddress;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCycle(String projectCycle) {
        this.projectCycle = projectCycle;
    }

    public String getProjectCycle() {
        return this.projectCycle;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCreateUser() {
        return this.createUser;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Date getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

}