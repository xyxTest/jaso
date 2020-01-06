package com.yaj.jaso.business.studyfile.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 视听学习资料+阅读学习资料
 * @date: 2019-10-24
 */
@TableName(value = "study_file")
public class StudyFilePO {
    /*
    *
    */
    @TableId
    private Long studyFileId;
    /*
    *标题
    */
    private String title;
    /*
    *学习资料url
    */
    private String fileUrl;
    /**
     *类型 (1、视听学习资料 2、阅读资料)
     */
    private Integer type;
    /**
     * 图片
     */
    private String pic;
    /*
    *
    */
    private Long studyWorkerTypeId;
    /*
    *文件大小
    */
    private String size;
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

    public void setStudyFileId(Long studyFileId) {
        this.studyFileId = studyFileId;
    }

    public Long getStudyFileId() {
        return this.studyFileId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setStudyWorkerTypeId(Long studyWorkerTypeId) {
        this.studyWorkerTypeId = studyWorkerTypeId;
    }

    public Long getStudyWorkerTypeId() {
        return this.studyWorkerTypeId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}