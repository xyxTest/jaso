package com.yaj.jaso.business.studydata.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 题目列表
 * @date: 2019-10-24
 */
@TableName(value = "study_data")
public class StudyDataPO {
    /*
    *
    */
    @TableId
    private Long studyDataId;
    /*
    *
    */
    private String dataName;
    /*
    *所有选项
    */
    private String studyDataOptions;
    /*
    *题目类别
    */
    private Long studyWorkerTypeId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long companyId;
    /**
     *正确答案 
     */
    private String rightKey;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *1、一般 2、容易 3、困难
    */
    private Integer difficultyLevel;
    /*
    *答案解析
    */
    private String answerAnalysis;

    public void setStudyDataId(Long studyDataId) {
        this.studyDataId = studyDataId;
    }

    public Long getStudyDataId() {
        return this.studyDataId;
    }


    public void setStudyWorkerTypeId(Long studyWorkerTypeId) {
        this.studyWorkerTypeId = studyWorkerTypeId;
    }

    public Long getStudyWorkerTypeId() {
        return this.studyWorkerTypeId;
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

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setAnswerAnalysis(String answerAnalysis) {
        this.answerAnalysis = answerAnalysis;
    }

    public String getAnswerAnalysis() {
        return this.answerAnalysis;
    }

	public String getRightKey() {
		return rightKey;
	}

	public void setRightKey(String rightKey) {
		this.rightKey = rightKey;
	}

	public String getStudyDataOptions() {
		return studyDataOptions;
	}

	public void setStudyDataOptions(String studyDataOptions) {
		this.studyDataOptions = studyDataOptions;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

}