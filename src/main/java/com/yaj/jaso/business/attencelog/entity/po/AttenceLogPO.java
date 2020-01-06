package com.yaj.jaso.business.attencelog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-11-05
 */
@TableName(value = "attence_log")
public class AttenceLogPO {
    /*
    *
    */
    @TableId
    private Long attenceLogId;
    /*
    *上班打卡时间
    */
    private Date startWorkTime;
    /*
    *下班打卡时间
    */
    private Date endWorkTime;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *0、正常 1、迟到
    */
    private Integer late;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long attenceModeId;
    /*
    *0、正常 1、早退
    */
    private Integer leaveEarly;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setAttenceLogId(Long attenceLogId) {
        this.attenceLogId = attenceLogId;
    }

    public Long getAttenceLogId() {
        return this.attenceLogId;
    }

    public void setStartWorkTime(Date startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Date getStartWorkTime() {
        return this.startWorkTime;
    }

    public void setEndWorkTime(Date endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public Date getEndWorkTime() {
        return this.endWorkTime;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public Integer getLate() {
        return this.late;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setAttenceModeId(Long attenceModeId) {
        this.attenceModeId = attenceModeId;
    }

    public Long getAttenceModeId() {
        return this.attenceModeId;
    }

    public void setLeaveEarly(Integer leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public Integer getLeaveEarly() {
        return this.leaveEarly;
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