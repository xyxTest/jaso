package com.yaj.jaso.business.studypersonalpreference.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 个人喜好（针对工种）记录表
 * @date: 2019-11-01
 */
@TableName(value = "study_personal_preference")
public class StudyPersonalPreferencePO {
    /*
    *
    */
    @TableId
    private Long studyPersonalPreferenceId;
    /*
    *
    */
    private Long studyWorkerTypeId;
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
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setStudyPersonalPreferenceId(Long studyPersonalPreferenceId) {
        this.studyPersonalPreferenceId = studyPersonalPreferenceId;
    }

    public Long getStudyPersonalPreferenceId() {
        return this.studyPersonalPreferenceId;
    }

    public void setStudyWorkerTypeId(Long studyWorkerTypeId) {
        this.studyWorkerTypeId = studyWorkerTypeId;
    }

    public Long getStudyWorkerTypeId() {
        return this.studyWorkerTypeId;
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