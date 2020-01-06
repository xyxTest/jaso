package com.yaj.jaso.business.userworktype.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 用户-工种关系表
 * @date: 2019-11-13
 */
@TableName(value = "user_work_type")
public class UserWorkTypePO {
    /*
    *
    */
    @TableId
    private Long userWorkTypeId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Long workTypeId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setUserWorkTypeId(Long userWorkTypeId) {
        this.userWorkTypeId = userWorkTypeId;
    }

    public Long getUserWorkTypeId() {
        return this.userWorkTypeId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Long getWorkTypeId() {
        return this.workTypeId;
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