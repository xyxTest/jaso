package com.yaj.jaso.business.worktask.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-19
 */
@TableName(value = "work_task")
public class WorkTaskPO {
    /*
    *
    */
    @TableId
    private Long workTaskId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
    *创建人
    */
    private Long jasoUserId;
    /*
    *评分
    */
    private Integer score;
    /*
    *重要程度（1、一般 2、重要 3、紧急）
    */
    private Integer state;
    /*
    *任务标题
    */
    private String taskTopic;
    /*
    *任务描述
    */
    private String taskDetail;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Date taskTime;
    /*
    *1、待接受 2、进行时 3、待验收 4、已完成
    */
    private Integer status;
    /*
    *
    */
    private String voiceFiles;
    /*
    *
    */
    private String imageFiles;
    /*
    *进度
    */
    private Integer process;
    
    private Date finishedDate;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setWorkTaskId(Long workTaskId) {
        this.workTaskId = workTaskId;
    }

    public Long getWorkTaskId() {
        return this.workTaskId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setTaskTopic(String taskTopic) {
        this.taskTopic = taskTopic;
    }

    public String getTaskTopic() {
        return this.taskTopic;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getTaskDetail() {
        return this.taskDetail;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public Date getTaskTime() {
        return this.taskTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setVoiceFiles(String voiceFiles) {
        this.voiceFiles = voiceFiles;
    }

    public String getVoiceFiles() {
        return this.voiceFiles;
    }

    public void setImageFiles(String imageFiles) {
        this.imageFiles = imageFiles;
    }

    public String getImageFiles() {
        return this.imageFiles;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getProcess() {
        return this.process;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

}