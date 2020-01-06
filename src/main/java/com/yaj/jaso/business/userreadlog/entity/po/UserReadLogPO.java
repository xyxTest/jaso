package com.yaj.jaso.business.userreadlog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-09
 */
@TableName(value = "user_read_log")
public class UserReadLogPO {
    /*
    *
    */
    @TableId
    private Long userReadLogId;
    /*
    *类型（1、新闻资讯）
    */
    private Integer type;
    /*
    *
    */
    private Long aboutId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *(1、未读 2、已读)
    */
    private Integer readState;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setUserReadLogId(Long userReadLogId) {
        this.userReadLogId = userReadLogId;
    }

    public Long getUserReadLogId() {
        return this.userReadLogId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
    }

    public Long getAboutId() {
        return this.aboutId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Integer getReadState() {
        return this.readState;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}