package com.yaj.jaso.business.reply.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@TableName(value = "reply")
public class ReplyPO {
    /*
    *
    */
    @TableId
    private Long replyId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private String replyContent;
    /*
    *1、实测实量的爆点管理 2、质量管理 3、安全管理 4、工作任务
    */
    private Integer replyType;
    /**
     *信息背景颜色 (1、白色 2、蓝色  3、红色 )
     */
    private Integer colorState;
    /*
    *
    */
    private Long aboutId;
    /*
    *
    */
    private Integer schedule;
    /*
    *
    */
    private String pictures;
    /*
    *
    */
    private String voices;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *
    */
    private Long companyId;
    /*
     *项目id 
     */
    private Long projectId;

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplyId() {
        return this.replyId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyContent() {
        return this.replyContent;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Integer getReplyType() {
        return this.replyType;
    }

    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
    }

    public Long getAboutId() {
        return this.aboutId;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public Integer getSchedule() {
        return this.schedule;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPictures() {
        return this.pictures;
    }

    public void setVoices(String voices) {
        this.voices = voices;
    }

    public String getVoices() {
        return this.voices;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getColorState() {
		return colorState;
	}

	public void setColorState(Integer colorState) {
		this.colorState = colorState;
	}

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

}