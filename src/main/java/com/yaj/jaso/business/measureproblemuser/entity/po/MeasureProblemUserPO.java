package com.yaj.jaso.business.measureproblemuser.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

/*
 * @Description: 
 * @date: 2019-09-02
 */
@TableName(value = "measure_problem_user")
public class MeasureProblemUserPO {
    /*
    *
    */
    @TableId
    private Long measureProblemUserId;
    /*
    *
    */
    private Long aboutId;
    /*
    *1、整改人 2、参与人
    */
    private Integer type;
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
    private Integer ifDelete;

    public void setMeasureProblemUserId(Long measureProblemUserId) {
        this.measureProblemUserId = measureProblemUserId;
    }

    public Long getMeasureProblemUserId() {
        return this.measureProblemUserId;
    }

    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
    }

    public Long getAboutId() {
        return this.aboutId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
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

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

}