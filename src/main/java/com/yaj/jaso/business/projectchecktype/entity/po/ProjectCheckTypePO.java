package com.yaj.jaso.business.projectchecktype.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-26
 */
@TableName(value = "project_check_type")
public class ProjectCheckTypePO {
    /*
    *
    */
    @TableId
    private Long projectCheckTypeId;
    /*
    *
    */
    private String checkName;
    /*
    *1、实测实量 2、质量管理 3、安全管理
    */
    private Integer checkType;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setProjectCheckTypeId(Long projectCheckTypeId) {
        this.projectCheckTypeId = projectCheckTypeId;
    }

    public Long getProjectCheckTypeId() {
        return this.projectCheckTypeId;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getCheckType() {
        return this.checkType;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
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

}