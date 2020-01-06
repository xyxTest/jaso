package com.yaj.jaso.business.folder.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-10-09
 */
@TableName(value = "folder")
public class FolderPO {
    /*
    *
    */
    @TableId
    private Long folderId;
    /*
    *
    */
    private String name;
    /*
    *
    */
    private String remark;
    /*
    *
    */
    private Integer fileType;
    /*
    *
    */
    private Long parrentId;
    /*
    *
    */
    private Long fileId;
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
    private String size;
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
    @TableLogic
    private Integer ifDelete;

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getFolderId() {
        return this.folderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getFileType() {
        return this.fileType;
    }

    public void setParrentId(Long parrentId) {
        this.parrentId = parrentId;
    }

    public Long getParrentId() {
        return this.parrentId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return this.fileId;
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

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}