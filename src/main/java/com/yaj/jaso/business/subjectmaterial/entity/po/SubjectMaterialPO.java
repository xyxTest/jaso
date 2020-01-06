package com.yaj.jaso.business.subjectmaterial.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "subject_material")
public class SubjectMaterialPO {
    /*
    *
    */
    @TableId
    private Long subjectMaterialId;
    /*
    *
    */
    private String subjectMaterialName;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setSubjectMaterialId(Long subjectMaterialId) {
        this.subjectMaterialId = subjectMaterialId;
    }

    public Long getSubjectMaterialId() {
        return this.subjectMaterialId;
    }

    public void setSubjectMaterialName(String subjectMaterialName) {
        this.subjectMaterialName = subjectMaterialName;
    }

    public String getSubjectMaterialName() {
        return this.subjectMaterialName;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}