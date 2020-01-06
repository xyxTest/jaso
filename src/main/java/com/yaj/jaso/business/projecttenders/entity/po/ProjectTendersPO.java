package com.yaj.jaso.business.projecttenders.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-07
 */
@TableName(value = "project_tenders")
public class ProjectTendersPO {
    /*
    *
    */
    @TableId
    private Long projectTendersId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private String tendersName;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setProjectTendersId(Long projectTendersId) {
        this.projectTendersId = projectTendersId;
    }

    public Long getProjectTendersId() {
        return this.projectTendersId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setTendersName(String tendersName) {
        this.tendersName = tendersName;
    }

    public String getTendersName() {
        return this.tendersName;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}