package com.yaj.jaso.business.constructlog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "construct_log")
public class ConstructLogPO {
    /*
    *
    */
    @TableId
    private Long constructLogId;
    /*
    *
    */
    private Long companyId;
    /*
    *项目id
    */
    private Long projectId;
    /*
    *标段
    */
    private Long projectTendersId;
    /*
    *天气
    */
    private String weather;
    /*
    *存在问题
    */
    private String existingProblems;
    /*
    *次日主材申请
    */
    private String subjectMaterialApplication;
    /*
    *次日机械申请
    */
    private String machineryApplication;
    /*
    *自检报告（0、合格 1、不合格 2、施工中）
    */
    private Integer selfCheckReport;
    /*
    *图片
    */
    private String pics;
    /*
    *施工日志日期
    */
    private Date constructDate;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setConstructLogId(Long constructLogId) {
        this.constructLogId = constructLogId;
    }

    public Long getConstructLogId() {
        return this.constructLogId;
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

    public void setProjectTendersId(Long projectTendersId) {
        this.projectTendersId = projectTendersId;
    }

    public Long getProjectTendersId() {
        return this.projectTendersId;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setExistingProblems(String existingProblems) {
        this.existingProblems = existingProblems;
    }

    public String getExistingProblems() {
        return this.existingProblems;
    }

    public void setSubjectMaterialApplication(String subjectMaterialApplication) {
        this.subjectMaterialApplication = subjectMaterialApplication;
    }

    public String getSubjectMaterialApplication() {
        return this.subjectMaterialApplication;
    }

    public void setMachineryApplication(String machineryApplication) {
        this.machineryApplication = machineryApplication;
    }

    public String getMachineryApplication() {
        return this.machineryApplication;
    }

    public void setSelfCheckReport(Integer selfCheckReport) {
        this.selfCheckReport = selfCheckReport;
    }

    public Integer getSelfCheckReport() {
        return this.selfCheckReport;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getPics() {
        return this.pics;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
    }

    public Date getConstructDate() {
        return this.constructDate;
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

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
}