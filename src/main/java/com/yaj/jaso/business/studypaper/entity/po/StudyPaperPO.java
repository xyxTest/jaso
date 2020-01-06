package com.yaj.jaso.business.studypaper.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-11-01
 */
@TableName(value = "study_paper")
public class StudyPaperPO {
    /*
    *
    */
    @TableId
    private Long studyPaperId;
    /*
    *
    */
    private String name;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long studyWorkerTypeId;
    /*
    *
    */
    private Long companyId;
    /*
    *题目idlist
    */
    private String studyDataList;
    /*
     *规定做题时间 
     */
    private Integer timeNum;
    /**
    *题目个数
    */
    private Integer dataNum;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setStudyPaperId(Long studyPaperId) {
        this.studyPaperId = studyPaperId;
    }

    public Long getStudyPaperId() {
        return this.studyPaperId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setStudyWorkerTypeId(Long studyWorkerTypeId) {
        this.studyWorkerTypeId = studyWorkerTypeId;
    }

    public Long getStudyWorkerTypeId() {
        return this.studyWorkerTypeId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setStudyDataList(String studyDataList) {
        this.studyDataList = studyDataList;
    }

    public String getStudyDataList() {
        return this.studyDataList;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Integer getTimeNum() {
		return timeNum;
	}

	public void setTimeNum(Integer timeNum) {
		this.timeNum = timeNum;
	}

	public Integer getDataNum() {
		return dataNum;
	}

	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}

}