package com.yaj.jaso.business.measureproblem.entity.vo;

import java.util.Date;
import java.util.List;

public class MeasureProblemAdd{
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
	private List<Long> rectifyUserIds;

	public List<Long> getRectifyUserIds() {
		return rectifyUserIds;
	}

	public void setRectifyUserIds(List<Long> rectifyUserIds) {
		this.rectifyUserIds = rectifyUserIds;
	}

	public Long getMeasureProblemId() {
		return measureProblemId;
	}

	public void setMeasureProblemId(Long measureProblemId) {
		this.measureProblemId = measureProblemId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCheckSite() {
		return checkSite;
	}

	public void setCheckSite(String checkSite) {
		this.checkSite = checkSite;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(Long checkUser) {
		this.checkUser = checkUser;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getMeasurePointId() {
		return measurePointId;
	}

	public void setMeasurePointId(Long measurePointId) {
		this.measurePointId = measurePointId;
	}

	public Long getProjectCheckTypeId() {
		return projectCheckTypeId;
	}

	public void setProjectCheckTypeId(Long projectCheckTypeId) {
		this.projectCheckTypeId = projectCheckTypeId;
	}

	public Integer getInputData() {
		return inputData;
	}

	public void setInputData(Integer inputData) {
		this.inputData = inputData;
	}

	public Long getMeasureSiteId() {
		return measureSiteId;
	}

	public void setMeasureSiteId(Long measureSiteId) {
		this.measureSiteId = measureSiteId;
	}

	public Long getRectifyUser() {
		return rectifyUser;
	}

	public void setRectifyUser(Long rectifyUser) {
		this.rectifyUser = rectifyUser;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}
	
}
