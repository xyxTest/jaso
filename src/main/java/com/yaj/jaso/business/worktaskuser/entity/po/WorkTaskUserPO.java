package com.yaj.jaso.business.worktaskuser.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-19
 */
@TableName(value = "work_task_user")
public class WorkTaskUserPO {
    /*
    *
    */
    @TableId
    private Long workTaskUserId;
    /*
    *1、执行人 2、参与人 
    */
    private Integer type;
    /*
    *
    */
    private Long workTaskId;
    /*
    *
    */
    private Long jasoUserId;
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
    @TableLogic
    private Integer ifDelete;

    public void setWorkTaskUserId(Long workTaskUserId) {
        this.workTaskUserId = workTaskUserId;
    }

    public Long getWorkTaskUserId() {
        return this.workTaskUserId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setWorkTaskId(Long workTaskId) {
        this.workTaskId = workTaskId;
    }

    public Long getWorkTaskId() {
        return this.workTaskId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}