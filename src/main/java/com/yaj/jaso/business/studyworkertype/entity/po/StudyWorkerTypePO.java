package com.yaj.jaso.business.studyworkertype.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-10-24
 */
@TableName(value = "study_worker_type")
public class StudyWorkerTypePO {
    /*
    *
    */
    @TableId
    private Long studyWorkerTypeId;
    /*
    *
    */
    private String name;
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

    public void setStudyWorkerTypeId(Long studyWorkerTypeId) {
        this.studyWorkerTypeId = studyWorkerTypeId;
    }

    public Long getStudyWorkerTypeId() {
        return this.studyWorkerTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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