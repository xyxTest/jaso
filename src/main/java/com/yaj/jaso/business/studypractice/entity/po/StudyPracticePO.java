package com.yaj.jaso.business.studypractice.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-11-01
 */
@TableName(value = "study_practice")
public class StudyPracticePO {
    /*
    *
    */
    @TableId
    private Long studyPracticeId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Long studyDataId;
    /*
    *个人答案
    */
    private String personalAnswer;
    /*
    *0、正确 1、不正确
    */
    private Integer isRight;
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

    public void setStudyPracticeId(Long studyPracticeId) {
        this.studyPracticeId = studyPracticeId;
    }

    public Long getStudyPracticeId() {
        return this.studyPracticeId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setStudyDataId(Long studyDataId) {
        this.studyDataId = studyDataId;
    }

    public Long getStudyDataId() {
        return this.studyDataId;
    }

    public void setPersonalAnswer(String personalAnswer) {
        this.personalAnswer = personalAnswer;
    }

    public String getPersonalAnswer() {
        return this.personalAnswer;
    }

    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

    public Integer getIsRight() {
        return this.isRight;
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