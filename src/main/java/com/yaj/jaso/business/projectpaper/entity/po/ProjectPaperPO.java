package com.yaj.jaso.business.projectpaper.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@TableName(value = "project_paper")
public class ProjectPaperPO {
    /*
    *
    */
    @TableId
    private Long projectPaperId;
    /*
    *
    */
    private String paperUrl;
    /*
    *
    */
    private String paperName;
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
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setProjectPaperId(Long projectPaperId) {
        this.projectPaperId = projectPaperId;
    }

    public Long getProjectPaperId() {
        return this.projectPaperId;
    }


    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public String getPaperUrl() {
        return this.paperUrl;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperName() {
        return this.paperName;
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