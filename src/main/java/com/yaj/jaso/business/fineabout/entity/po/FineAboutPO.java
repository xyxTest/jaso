package com.yaj.jaso.business.fineabout.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-07
 */
@TableName(value = "fine_about")
public class FineAboutPO {
    /*
    *
    */
    @TableId
    private Long fineAboutId;
    /*
    *1、质量奖惩 2、安全奖惩
    */
    private Integer type;
    /*
    *
    */
    private Long qualityOrSecurityId;
    /*
    *1、责任到人  2、责任到部门
    */
    private Integer state;
    /*
    *
    */
    private Long aboutId;
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

    public void setFineAboutId(Long fineAboutId) {
        this.fineAboutId = fineAboutId;
    }

    public Long getFineAboutId() {
        return this.fineAboutId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setQualityOrSecurityId(Long qualityOrSecurityId) {
        this.qualityOrSecurityId = qualityOrSecurityId;
    }

    public Long getQualityOrSecurityId() {
        return this.qualityOrSecurityId;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
    }

    public Long getAboutId() {
        return this.aboutId;
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