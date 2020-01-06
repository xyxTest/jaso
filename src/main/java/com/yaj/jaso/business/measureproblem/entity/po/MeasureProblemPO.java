package com.yaj.jaso.business.measureproblem.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@TableName(value = "measure_problem")
public class MeasureProblemPO {
    /*
    *
    */
    @TableId
    private Long measureProblemId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private String checkSite;
    /*
    *
    */
    private Date checkDate;
    /*
    *检查人
    */
    private Long checkUser;
    /*
    *
    */
    private String detail;
    /*
     *测点id 
     */
    private Long measurePointId;
    /*
    *检查项id
    */
    private Long projectCheckTypeId;
    /*
     *测量数据
     */
     private Integer inputData;
    /*
     *房间id 
     */
    private Long measureSiteId;
    /*
    *
    */
    private Long rectifyUser;
    /*
    *限定整改完成时间
    */
    private Date finishedDate;
    /*
    *整改进度
    */
    private Integer process;
    /*
    *录音
    */
    private String voiceFiles;
    /*
     *图片 
     */
    private String imageFiles;
    /*
    *1、待指派、2、进行中、3、待验收 4、已完成
    */
    private Integer status;
    /*
    *
    */
    private Integer score;
    /*
    *
    */
    private Date createTime;
    /*
    *创建人
    */
    private Long jasoUserId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setMeasureProblemId(Long measureProblemId) {
        this.measureProblemId = measureProblemId;
    }

    public Long getMeasureProblemId() {
        return this.measureProblemId;
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

    public void setCheckSite(String checkSite) {
        this.checkSite = checkSite;
    }

    public String getCheckSite() {
        return this.checkSite;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckUser(Long checkUser) {
        this.checkUser = checkUser;
    }

    public Long getCheckUser() {
        return this.checkUser;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return this.detail;
    }


    public void setRectifyUser(Long rectifyUser) {
        this.rectifyUser = rectifyUser;
    }

    public Long getRectifyUser() {
        return this.rectifyUser;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Date getFinishedDate() {
        return this.finishedDate;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Integer getProcess() {
        return this.process;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public Long getProjectCheckTypeId() {
		return projectCheckTypeId;
	}

	public void setProjectCheckTypeId(Long projectCheckTypeId) {
		this.projectCheckTypeId = projectCheckTypeId;
	}

	public Long getMeasurePointId() {
		return measurePointId;
	}

	public void setMeasurePointId(Long measurePointId) {
		this.measurePointId = measurePointId;
	}

	public Long getMeasureSiteId() {
		return measureSiteId;
	}

	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}

	public Integer getInputData() {
		return inputData;
	}

	public void setInputData(Integer inputData) {
		this.inputData = inputData;
	}

	public String getVoiceFiles() {
		return voiceFiles;
	}

	public void setVoiceFiles(String voiceFiles) {
		this.voiceFiles = voiceFiles;
	}

	public String getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(String imageFiles) {
		this.imageFiles = imageFiles;
	}


}