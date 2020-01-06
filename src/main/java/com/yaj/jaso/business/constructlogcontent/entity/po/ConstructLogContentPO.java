package com.yaj.jaso.business.constructlogcontent.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "construct_log_content")
public class ConstructLogContentPO {
    /*
    *
    */
    @TableId
    private Long constructLogContentId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long projectId;
    /*
    *生产情况-开始时间
    */
    private String productionStartTime;
    /*
    *生产情况-结束时间
    */
    private String productionEndTime;
    /*
    *生产情况-施工部位
    */
    private String productionConstructPart;
    /*
    *生产情况-施工内容
    */
    private String productionConstructContent;
    /*
    *生产情况-完成工作量
    */
    private String productionWorkLoad;
    /*
    *工作内容-内容分类
    */
    private String jobContentContentType;
    /*
    *工作内容-内容描述
    */
    private String jobConentContentDescribe;
    /*
    *工作内容-备注
    */
    private String jobContentRemark;
    /*
    *施工进度-开始时间
    */
    private String constructProgressStartTime;
    /*
    *施工进度-结束时间
    */
    private String constructProgressEndTime;
    /*
    *施工进度-施工部位
    */
    private String constructProgressConstructPart;
    /*
    *施工进度-施工内容
    */
    private String constructProgressConstructContent;
    /*
    *施工进度-班组
    */
    private String constructProgressTeams;
    /*
    *施工内容-工作进度
    */
    private String constructProgressNums;
    /*
    *工作事务-内容类型
    */
    private String workThingsContentType;
    /*
    *工作事务-内容描述
    */
    private String workThingsContentDescribe;
    /*
    *工作事务-备注
    */
    private String workThingsRemark;
    /*
    *施工日志id
    */
    private Long constructLogId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setConstructLogContentId(Long constructLogContentId) {
        this.constructLogContentId = constructLogContentId;
    }

    public Long getConstructLogContentId() {
        return this.constructLogContentId;
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

    public void setProductionStartTime(String productionStartTime) {
        this.productionStartTime = productionStartTime;
    }

    public String getProductionStartTime() {
        return this.productionStartTime;
    }

    public void setProductionEndTime(String productionEndTime) {
        this.productionEndTime = productionEndTime;
    }

    public String getProductionEndTime() {
        return this.productionEndTime;
    }

    public void setProductionConstructPart(String productionConstructPart) {
        this.productionConstructPart = productionConstructPart;
    }

    public String getProductionConstructPart() {
        return this.productionConstructPart;
    }

    public void setProductionConstructContent(String productionConstructContent) {
        this.productionConstructContent = productionConstructContent;
    }

    public String getProductionConstructContent() {
        return this.productionConstructContent;
    }

    public void setProductionWorkLoad(String productionWorkLoad) {
        this.productionWorkLoad = productionWorkLoad;
    }

    public String getProductionWorkLoad() {
        return this.productionWorkLoad;
    }

    public void setJobContentContentType(String jobContentContentType) {
        this.jobContentContentType = jobContentContentType;
    }

    public String getJobContentContentType() {
        return this.jobContentContentType;
    }

    public void setJobConentContentDescribe(String jobConentContentDescribe) {
        this.jobConentContentDescribe = jobConentContentDescribe;
    }

    public String getJobConentContentDescribe() {
        return this.jobConentContentDescribe;
    }

    public void setJobContentRemark(String jobContentRemark) {
        this.jobContentRemark = jobContentRemark;
    }

    public String getJobContentRemark() {
        return this.jobContentRemark;
    }

    public void setConstructProgressStartTime(String constructProgressStartTime) {
        this.constructProgressStartTime = constructProgressStartTime;
    }

    public String getConstructProgressStartTime() {
        return this.constructProgressStartTime;
    }

    public void setConstructProgressEndTime(String constructProgressEndTime) {
        this.constructProgressEndTime = constructProgressEndTime;
    }

    public String getConstructProgressEndTime() {
        return this.constructProgressEndTime;
    }

    public void setConstructProgressConstructPart(String constructProgressConstructPart) {
        this.constructProgressConstructPart = constructProgressConstructPart;
    }

    public String getConstructProgressConstructPart() {
        return this.constructProgressConstructPart;
    }

    public void setConstructProgressConstructContent(String constructProgressConstructContent) {
        this.constructProgressConstructContent = constructProgressConstructContent;
    }

    public String getConstructProgressConstructContent() {
        return this.constructProgressConstructContent;
    }

    public void setConstructProgressTeams(String constructProgressTeams) {
        this.constructProgressTeams = constructProgressTeams;
    }

    public String getConstructProgressTeams() {
        return this.constructProgressTeams;
    }

    public void setConstructProgressNums(String constructProgressNums) {
        this.constructProgressNums = constructProgressNums;
    }

    public String getConstructProgressNums() {
        return this.constructProgressNums;
    }

    public void setWorkThingsContentType(String workThingsContentType) {
        this.workThingsContentType = workThingsContentType;
    }

    public String getWorkThingsContentType() {
        return this.workThingsContentType;
    }

    public void setWorkThingsContentDescribe(String workThingsContentDescribe) {
        this.workThingsContentDescribe = workThingsContentDescribe;
    }

    public String getWorkThingsContentDescribe() {
        return this.workThingsContentDescribe;
    }

    public void setWorkThingsRemark(String workThingsRemark) {
        this.workThingsRemark = workThingsRemark;
    }

    public String getWorkThingsRemark() {
        return this.workThingsRemark;
    }

    public void setConstructLogId(Long constructLogId) {
        this.constructLogId = constructLogId;
    }

    public Long getConstructLogId() {
        return this.constructLogId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}