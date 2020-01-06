package com.yaj.jaso.business.userintegrallog.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 用户学习模块的积分记录表
 * @date: 2019-11-04
 */
@TableName(value = "user_integral_log")
public class UserIntegralLogPO {
    /*
    *
    */
    @TableId
    private Long userIntegralLogId;
    /*
    *
    */
    private Integer num;
    /*
    *1、签到（一天一次，一次一分）2、阅读文章（一天6篇，一篇一分）3、视听学习（一天6个，一个一分）4、题目（一天6个，一个一分）5、试卷答题（超80分得三分，其他得一分，封顶两套
    */
    private Integer type;
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
    private Long companyId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setUserIntegralLogId(Long userIntegralLogId) {
        this.userIntegralLogId = userIntegralLogId;
    }

    public Long getUserIntegralLogId() {
        return this.userIntegralLogId;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
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