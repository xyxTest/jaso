package com.yaj.jaso.business.machinery.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.util.Date;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "machinery")
public class MachineryPO {
    /*
    *
    */
    @TableId
    private Long machineryId;
    /*
    *机械名称
    */
    private String machineryName;
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
    @TableLogic
    private Integer ifDelete;
    /*
    *
    */
    private Date createTime;

    public void setMachineryId(Long machineryId) {
        this.machineryId = machineryId;
    }

    public Long getMachineryId() {
        return this.machineryId;
    }

    public void setMachineryName(String machineryName) {
        this.machineryName = machineryName;
    }

    public String getMachineryName() {
        return this.machineryName;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

}