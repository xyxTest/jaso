package com.yaj.jaso.business.workdepartmentrule.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.util.Date;

/*
 * @Description: 
 * @date: 2019-12-06
 */
@TableName(value = "work_department_rule")
public class WorkDepartmentRulePO {
    /*
    *
    */
    @TableId
    private Long workDepartmentRuleId;
    /*
    *
    */
    private Long departmentId;
    /*
    *开始
    */
    private Integer calcStartDay;
    /*
    *结束
    */
    private Integer calcEndDay;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *
    */
    private Date createTime;
    /*
    *休息：1,2,3
    */
    private String workRestDays;
    /*
    *
    */
    private String ruleName;

    public void setWorkDepartmentRuleId(Long workDepartmentRuleId) {
        this.workDepartmentRuleId = workDepartmentRuleId;
    }

    public Long getWorkDepartmentRuleId() {
        return this.workDepartmentRuleId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setCalcStartDay(Integer calcStartDay) {
        this.calcStartDay = calcStartDay;
    }

    public Integer getCalcStartDay() {
        return this.calcStartDay;
    }

    public void setCalcEndDay(Integer calcEndDay) {
        this.calcEndDay = calcEndDay;
    }

    public Integer getCalcEndDay() {
        return this.calcEndDay;
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

    public void setWorkRestDays(String workRestDays) {
        this.workRestDays = workRestDays;
    }

    public String getWorkRestDays() {
        return this.workRestDays;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return this.ruleName;
    }

}