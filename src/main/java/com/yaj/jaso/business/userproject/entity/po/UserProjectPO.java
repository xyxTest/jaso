package com.yaj.jaso.business.userproject.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;


/*
 * @Description: 
 * @date: 2019-07-22
 */
@TableName(value = "user_project")
public class UserProjectPO {
    /*
    *
    */
    @TableId
    private Long userProjectId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    @TableLogic
    private Integer ifDelete;

    public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	public void setUserProjectId(Long userProjectId) {
        this.userProjectId = userProjectId;
    }

    public Long getUserProjectId() {
        return this.userProjectId;
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

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

}