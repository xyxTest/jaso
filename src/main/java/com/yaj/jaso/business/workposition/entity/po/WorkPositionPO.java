package com.yaj.jaso.business.workposition.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.math.BigDecimal;

/*
 * @Description: 
 * @date: 2019-12-06
 */
@TableName(value = "work_position")
public class WorkPositionPO {
    /*
    *
    */
    @TableId
    private Long positionId;
    /*
    *地点名称
    */
    private String positionName;
    /*
    *经度
    */
    private BigDecimal positionLongitude;
    /*
    *纬度
    */
    private BigDecimal positionLatitude;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private String workStartTime;
    /*
    *
    */
    private String workEndTime;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *半径(米)
    */
    private Integer positionRange;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private String nickName;
    /*
    *
    */
    private Long workDepartmentRuleId;

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getPositionId() {
        return this.positionId;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionLongitude(BigDecimal positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public BigDecimal getPositionLongitude() {
        return this.positionLongitude;
    }

    public void setPositionLatitude(BigDecimal positionLatitude) {
        this.positionLatitude = positionLatitude;
    }

    public BigDecimal getPositionLatitude() {
        return this.positionLatitude;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setWorkStartTime(String workStartTime) {
        this.workStartTime = workStartTime;
    }

    public String getWorkStartTime() {
        return this.workStartTime;
    }

    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getWorkEndTime() {
        return this.workEndTime;
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

    public void setPositionRange(Integer positionRange) {
        this.positionRange = positionRange;
    }

    public Integer getPositionRange() {
        return this.positionRange;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setWorkDepartmentRuleId(Long workDepartmentRuleId) {
        this.workDepartmentRuleId = workDepartmentRuleId;
    }

    public Long getWorkDepartmentRuleId() {
        return this.workDepartmentRuleId;
    }

}