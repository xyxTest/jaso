package com.yaj.jaso.business.workrecord.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-12-04
 */
@TableName(value = "work_record")
public class WorkRecordPO {
    /*
    *
    */
    @TableId
    private Long workRecordId;
    /*
    *
    */
    private Long positionId;
    /*
    *
    */
    private Date workRecordTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *1: 是，0： 否
    */
    private Integer workLate;
    /*
    *1: 是，0： 否
    */
    private Integer workLeaveEarly;
    /*
    *1: 上班卡,2: 下班卡
    */
    private Integer workRecordType;

    public void setWorkRecordId(Long workRecordId) {
        this.workRecordId = workRecordId;
    }

    public Long getWorkRecordId() {
        return this.workRecordId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getPositionId() {
        return this.positionId;
    }

    public void setWorkRecordTime(Date workRecordTime) {
        this.workRecordTime = workRecordTime;
    }

    public Date getWorkRecordTime() {
        return this.workRecordTime;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
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

    public void setWorkLate(Integer workLate) {
        this.workLate = workLate;
    }

    public Integer getWorkLate() {
        return this.workLate;
    }

    public void setWorkLeaveEarly(Integer workLeaveEarly) {
        this.workLeaveEarly = workLeaveEarly;
    }

    public Integer getWorkLeaveEarly() {
        return this.workLeaveEarly;
    }

    public void setWorkRecordType(Integer workRecordType) {
        this.workRecordType = workRecordType;
    }

    public Integer getWorkRecordType() {
        return this.workRecordType;
    }

}