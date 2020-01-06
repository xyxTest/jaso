package com.yaj.jaso.business.studyanswerrecord.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-11-01
 */
@TableName(value = "study_answer_record")
public class StudyAnswerRecordPO {
    /*
    *
    */
    @TableId
    private Long studyAnswerRecordId;
    /*
    *试卷id
    */
    private Long studyPaperId;
    /**
     *试卷的题目数 
     */
    private Integer dataNum;
    /*
    *题目id
    */
    private Long studyDataId;
    /*
    *用户id
    */
    private Long jasoUserId;
    /*
    *个人答题选项
    */
    private String personalAnswer;
    /*
    *0、错误、1、正确
    */
    private Integer isRight;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setStudyAnswerRecordId(Long studyAnswerRecordId) {
        this.studyAnswerRecordId = studyAnswerRecordId;
    }

    public Long getStudyAnswerRecordId() {
        return this.studyAnswerRecordId;
    }

    public void setStudyPaperId(Long studyPaperId) {
        this.studyPaperId = studyPaperId;
    }

    public Long getStudyPaperId() {
        return this.studyPaperId;
    }

    public void setStudyDataId(Long studyDataId) {
        this.studyDataId = studyDataId;
    }

    public Long getStudyDataId() {
        return this.studyDataId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
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

	public Integer getDataNum() {
		return dataNum;
	}

	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}

}